package com.shlyankin.imagesearcher.ui.photos.adapter

import androidx.recyclerview.widget.RecyclerView
import com.shlyankin.imagesearcher.R
import com.shlyankin.imagesearcher.databinding.ItemPhotoBinding
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto
import com.shlyankin.imagesearcher.glide.GlideApp

class PhotoViewHolder(
    private val viewBinding: ItemPhotoBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    private companion object {
        const val SIZE_MULTIPLIER = 0.5f
        const val HEIGHT = 1280
        const val WIDTH = 1280
    }

    private fun loadImage(uri: String, errorUri: String? = null): Boolean {
        viewBinding.run {
            GlideApp.with(root)
                .load(uri)
                .apply {
                    if (errorUri != null) {
                        error(
                            GlideApp
                                .with(root)
                                .load(errorUri)
                        )
                    }
                }
                .override(WIDTH, HEIGHT)
                .thumbnail(SIZE_MULTIPLIER)
                .placeholder(R.drawable.ic_launcher)
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
            } ?: loadImage(photo.urls.full)
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