package com.shlyankin.photos.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shlyankin.photos.R
import com.shlyankin.photos.databinding.FPhotosBinding
import com.shlyankin.photos.ui.photos.adapter.PhotosAdapter
import com.shlyankin.photos.ui.photos.adapter.load.PhotosLoadStateAdapter
import com.shlyankin.photos.ui.photos.refresh.SwipeRefreshViewModel
import com.shlyankin.util.BindingFragment
import com.shlyankin.util.utils.observe
import com.shlyankin.util.utils.observeLatest
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class PhotosFragment : BindingFragment<FPhotosBinding>() {

    internal open val viewModel: PhotosViewModel by viewModels<PhotosViewModelImpl>()
    private val swipeRefreshViewModel by viewModels<SwipeRefreshViewModel>()
    private val photosAdapter by lazy { PhotosAdapter(viewModel::addToFavouriteClicked) }

    override fun initBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FPhotosBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
    }

    override fun FPhotosBinding.onInitViews() {
        photos.adapter =
            photosAdapter.withLoadStateFooter(footer = PhotosLoadStateAdapter(photosAdapter::retry))
        photosAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
        photosAdapter.addLoadStateListener { loadStates ->
            swipeRefreshViewModel.onLoadStateChanged(loadStates)
        }
        photos.layoutManager = LinearLayoutManager(requireContext())
        photos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.HORIZONTAL
            )
        )
        photoSwipeRefresh.setOnRefreshListener {
            swipeRefreshViewModel.onSwipeRefresh()
        }
    }

    private fun observeViewModel() {
        swipeRefreshViewModel.run {
            observeLatest(currentState) {
                binding.run {
                    photoSwipeRefresh.isRefreshing = it.isRefreshing
                    if (it.isError) {
                        Toast.makeText(
                            requireContext(),
                            R.string.page_loading_error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
            observe(refreshEvent) {
                photosAdapter.refresh()
            }
        }
        viewModel.run {
            observeLatest(photos) {
                photosAdapter.submitData(it)
            }
        }
    }


}