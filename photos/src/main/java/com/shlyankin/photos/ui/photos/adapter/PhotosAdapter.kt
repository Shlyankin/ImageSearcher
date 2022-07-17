package com.shlyankin.photos.ui.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.shlyankin.photos.databinding.ItemPhotoBinding
import com.shlyankin.photos.model.PresPhoto

internal class PhotosAdapter(
    private val itemClickListener: (photo: PresPhoto) -> Unit,
    private val onFavouriteClickListener: (photo: PresPhoto) -> Unit,
) : PagingDataAdapter<PresPhoto, PhotoViewHolder>(PhotosDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        getItem(position)?.let { item ->
            holder.bind(item, itemClickListener, onFavouriteClickListener)
        }
    }
}

