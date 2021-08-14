package com.shlyankin.imagesearcher.ui.photos.adapter.load

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.shlyankin.imagesearcher.databinding.ItemLoadPhotoBinding
import com.shlyankin.imagesearcher.utils.emptyFun

class PhotosLoadStateAdapter(
    private val onRetryClicked: () -> Unit = emptyFun()
) : LoadStateAdapter<PhotosLoadViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): PhotosLoadViewHolder {
        return PhotosLoadViewHolder(
            ItemLoadPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotosLoadViewHolder, loadState: LoadState) {
        holder.bind(loadState, onRetryClicked)
    }
}