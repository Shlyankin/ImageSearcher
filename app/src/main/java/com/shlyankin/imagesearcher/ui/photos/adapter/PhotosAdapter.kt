package com.shlyankin.imagesearcher.ui.photos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.shlyankin.imagesearcher.databinding.ItemPhotoBinding
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto

// todo: запасной адаптер, если groupie потом не подойдет
class PhotosAdapter(
    private val onFavouriteClickListener: (photo: UiPhoto) -> Unit
) : ListAdapter<UiPhoto, PhotoViewHolder>(PhotosDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        holder.bind(getItem(position), onFavouriteClickListener)
    }
}

