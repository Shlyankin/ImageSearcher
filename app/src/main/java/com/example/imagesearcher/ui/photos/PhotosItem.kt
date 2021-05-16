package com.example.imagesearcher.ui.photos

import android.view.View
import com.bumptech.glide.Glide
import com.example.imagesearcher.R
import com.example.imagesearcher.databinding.ItemPhotoBinding
import com.example.imagesearcher.domain.model.ui.UiPhoto
import com.example.imagesearcher.utils.loadFileOrUrl
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

class PhotosItem(
    private val photo: UiPhoto,
    var onFavouriteClickListener: (photo: UiPhoto) -> Unit = { _ -> }
) : BindableItem<ItemPhotoBinding>() {

    override fun bind(viewBinding: ItemPhotoBinding, position: Int) {
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

    override fun isSameAs(other: Item<*>): Boolean {
        val otherItem = other as? PhotosItem ?: return false
        return otherItem.photo.id == photo.id
    }

    override fun hasSameContentAs(other: Item<*>): Boolean {
        val otherItem = other as? PhotosItem ?: return false
        return otherItem.photo == photo
    }

    override fun getLayout(): Int = R.layout.item_photo

    override fun initializeViewBinding(view: View): ItemPhotoBinding {
        return ItemPhotoBinding.bind(view)
    }
}