package com.shlyankin.photos.ui.photos.adapter

import androidx.recyclerview.widget.RecyclerView
import com.shlyankin.photos.R
import com.shlyankin.photos.databinding.ItemPhotoBinding
import com.shlyankin.photos.model.UiPhoto
import com.shlyankin.util.glide.GlideApp
import com.shlyankin.util.utils.alternativeUri

internal class PhotoViewHolder(
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
                .alternativeUri(root, errorUri)
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
        itemClickListener: (photo: UiPhoto) -> Unit,
        onFavouriteClickListener: (photo: UiPhoto) -> Unit
    ) {
        viewBinding.run {
            root.setOnClickListener { itemClickListener.invoke(photo) }
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