package com.shlyankin.imagesearcher.ui.photos.adapter.load

import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import com.shlyankin.imagesearcher.databinding.ItemLoadPhotoBinding

class PhotosLoadViewHolder(
    private val viewBinding: ItemLoadPhotoBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    private fun handleLoadingState() {
        viewBinding.run {
            circularProgressIndicator.isVisible = true
            errorGroup.isVisible = false
            endPageText.isVisible = false
        }
    }

    private fun handleErrorState(onRetryClicked: () -> Unit) {
        viewBinding.run {
            circularProgressIndicator.isVisible = false
            errorGroup.isVisible = true
            retryButton.setOnClickListener {
                onRetryClicked.invoke()
            }
            endPageText.isVisible = false
        }
    }

    private fun handleEndState() {
        viewBinding.run {
            circularProgressIndicator.isVisible = false
            errorGroup.isVisible = false
            endPageText.isVisible = true
        }
    }

    fun bind(loadState: LoadState, onRetryClicked: () -> Unit) {
        when (loadState) {
            is LoadState.Error -> {
                handleErrorState(onRetryClicked)
            }
            is LoadState.Loading -> {
                handleLoadingState()
            }
            is LoadState.NotLoading -> {
                handleEndState()
            }
        }
    }

}