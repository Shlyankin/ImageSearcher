package com.shlyankin.photos.ui.photos.adapter.load

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import com.shlyankin.photos.databinding.ItemLoadPhotoBinding
import com.shlyankin.util.utils.emptyFun

internal class PhotosLoadStateAdapter(
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