package com.shlyankin.imagesearcher.ui.photos.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shlyankin.imagesearcher.databinding.FPhotosBinding
import com.shlyankin.imagesearcher.ui.BindingFragment
import com.shlyankin.imagesearcher.ui.photos.adapter.PhotosAdapter
import com.shlyankin.imagesearcher.utils.observeLatest
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
        photosAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
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
            observeLatest(favouritePhotos) {
                photosAdapter.submitData(it)
            }
        }
    }
}