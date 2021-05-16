package com.example.imagesearcher.ui.photos.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imagesearcher.R
import com.example.imagesearcher.databinding.ItemPhotoBinding
import com.example.imagesearcher.domain.model.ui.UiPhoto
import com.example.imagesearcher.utils.loadFileOrUrl

class PhotoViewHolder(
    private val viewBinding: ItemPhotoBinding
) : RecyclerView.ViewHolder(
    viewBinding.root
) {
    fun bind(
        photo: UiPhoto,
        onFavouriteClickListener: (photo: UiPhoto) -> Unit
    ) {
        viewBinding.run {
            title.text = photo.description
            userName.text = photo.user.name
            Glide.with(root)
                .loadFileOrUrl(photo.localPath, photo.urls.regular)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_error)
                .centerCrop()
                .into(image)
            addToFavourite.setImageResource(
                if (photo.isFavourite) {
                    R.drawable.ic_favourite
                } else {
                    R.drawable.ic_empty_favourite
                }
            )
            addToFavourite.setOnClickListener {
                onFavouriteClickListener.invoke(photo)
            }
        }
    }
}