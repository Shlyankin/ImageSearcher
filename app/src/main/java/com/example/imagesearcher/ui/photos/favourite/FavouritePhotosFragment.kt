package com.example.imagesearcher.ui.photos.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imagesearcher.databinding.FPhotosBinding
import com.example.imagesearcher.ui.BindingFragment
import com.example.imagesearcher.ui.photos.adapter.PhotosAdapter
import com.example.imagesearcher.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavouritePhotosFragment : BindingFragment<FPhotosBinding>() {

    private val viewModel by viewModels<FavouritePhotosViewModel>()
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
        viewModel.run {
            observe(favouritePhotos) {
                photosAdapter.submitList(it)
            }
        }
    }
}