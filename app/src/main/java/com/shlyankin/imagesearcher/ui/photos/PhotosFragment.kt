package com.shlyankin.imagesearcher.ui.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shlyankin.imagesearcher.R
import com.shlyankin.imagesearcher.databinding.FPhotosBinding
import com.shlyankin.imagesearcher.ui.BindingFragment
import com.shlyankin.imagesearcher.ui.photos.adapter.PhotosAdapter
import com.shlyankin.imagesearcher.ui.photos.adapter.load.PhotosLoadStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
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
        photos.adapter =
            photosAdapter.withLoadStateFooter(footer = PhotosLoadStateAdapter(photosAdapter::retry))
        photosAdapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.ALLOW
        photosAdapter.addLoadStateListener { loadStates ->
            viewModel.onLoadStateChanged(loadStates)
        }
        photos.layoutManager = LinearLayoutManager(requireContext())
        photos.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.HORIZONTAL
            )
        )
        retryButton.setOnClickListener {
            photosAdapter.retry()
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            viewModel.photos.collectLatest {
                photosAdapter.submitData(it)
            }
        }
        lifecycleScope.launch {
            viewModel.currentState.collectLatest {
                binding.run {
                    retryButton.isVisible = it.retryVisible
                    loadingIndicator.isVisible = it.progressVisible
                    if (it.errorVisible) {
                        Toast.makeText(
                            requireContext(),
                            R.string.page_loading_error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }


}