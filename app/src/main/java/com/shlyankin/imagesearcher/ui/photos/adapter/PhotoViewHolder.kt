package com.shlyankin.imagesearcher.ui.photos.adapter

import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.shlyankin.imagesearcher.R
import com.shlyankin.imagesearcher.databinding.ItemPhotoBinding
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto
import com.shlyankin.imagesearcher.utils.loadFileOrUrl
import java.util.logging.Logger

class MyRequestListener : RequestListener<Drawable> {

    private val logger = Logger.getLogger(MyRequestListener::class.simpleName)

    override fun onLoadFailed(
        e: GlideException?,
        model: Any?,
        target: Target<Drawable>?,
        isFirstResource: Boolean
    ): Boolean {
        logger.info("onResourceReady e: $e, model: $model, target: $target, isFirstResource: $isFirstResource")
        return false
    }

    override fun onResourceReady(
        resource: Drawable?,
        model: Any?,
        target: Target<Drawable>?,
        dataSource: DataSource?,
        isFirstResource: Boolean
    ): Boolean {
        logger.info("onResourceReady resource: $resource, model: $model, target: $target, dataSource: $dataSource, isFirstResource: $isFirstResource")
        return false
    }
}

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
                .addListener(MyRequestListener())
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