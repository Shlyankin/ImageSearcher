package com.shlyankin.imagesearcher.ui.photos.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shlyankin.imagesearcher.R
import com.shlyankin.imagesearcher.databinding.ItemPhotoBinding
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto

class PhotoViewHolder(
    private val viewBinding: ItemPhotoBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    private fun loadImage(uri: String, errorUri: String? = null): Boolean {
        viewBinding.run {
            Glide.with(root)
                .load(uri)
                .placeholder(R.drawable.ic_launcher)
                .apply {
                    if (errorUri != null) {
                        error(Glide.with(root).load(errorUri))
                    }
                }
                .error(R.drawable.ic_error)
                .centerCrop()
                .into(image)
        }
        return true
    }

    fun bind(
        photo: UiPhoto,
        onFavouriteClickListener: (photo: UiPhoto) -> Unit
    ) {
        viewBinding.run {
            title.text = photo.description
            userName.text = photo.user.name
            photo.localPath?.let { localPath ->
                loadImage(localPath, errorUri = photo.urls.regular)
            } ?: loadImage(photo.urls.regular)
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