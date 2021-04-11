package com.example.imagesearcher.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.imagesearcher.databinding.FPhotosBinding
import com.example.imagesearcher.ui.BindingFragment
import com.xwray.groupie.GroupieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotosFragment : BindingFragment<FPhotosBinding>() {

    private val photosViewModel by viewModels<PhotosViewModel>()
    private val photosAdapter by lazy { GroupieAdapter() }

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
            photosViewModel.photos.collect {
                photosAdapter.updateAsync(it.map { photo -> PhotosItem(photo) })
            }
        }
    }
}