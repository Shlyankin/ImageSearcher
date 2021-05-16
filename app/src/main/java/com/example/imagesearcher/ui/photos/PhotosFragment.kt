package com.example.imagesearcher.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagesearcher.databinding.FPhotosBinding
import com.example.imagesearcher.ui.BindingFragment
import com.example.imagesearcher.ui.photos.adapter.PhotosAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosFragment : BindingFragment<FPhotosBinding>() {

    private val viewModel by viewModels<PhotosViewModel>()
    private val photosAdapter by lazy { PhotosAdapter(viewModel::addToFavouriteClicked) }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FPhotosBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    override fun FPhotosBinding.onInitViews() {
        photos.adapter = photosAdapter
        photos.layoutManager = LinearLayoutManager(requireContext())
        photos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.HORIZONTAL
            )
        )
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.photos.collect {
                photosAdapter.submitList(it)
            }
        }
    }
}