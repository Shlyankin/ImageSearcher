package com.shlyankin.imagesearcher.ui.photos.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.shlyankin.imagesearcher.R
import com.shlyankin.imagesearcher.databinding.ItemPhotoBinding
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto
import com.shlyankin.imagesearcher.utils.GlideErrorRequestListener

class PhotoViewHolder(
    private val viewBinding: ItemPhotoBinding
) : RecyclerView.ViewHolder(viewBinding.root) {

    private fun loadImage(uri: String, onLoadFailed: () -> Boolean = { false }): Boolean {
        viewBinding.run {
            Glide.with(root)
                .load(uri)
                .listener(GlideErrorRequestListener(onLoadFailed))
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
                loadImage(localPath, onLoadFailed = { loadImage(photo.urls.regular) })
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