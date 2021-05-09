package com.example.imagesearcher.ui.photos

import android.view.View
import com.bumptech.glide.Glide
import com.example.imagesearcher.R
import com.example.imagesearcher.databinding.ItemPhotoBinding
import com.example.imagesearcher.domain.model.ui.UiPhoto
import com.xwray.groupie.viewbinding.BindableItem

class PhotosItem(private val photo: UiPhoto) : BindableItem<ItemPhotoBinding>() {

    override fun bind(viewBinding: ItemPhotoBinding, position: Int) {
        viewBinding.run {
            title.text = photo.description
            userName.text = photo.user.name
            Glide.with(root)
                .load(photo.urls.regular)
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_error)
                .centerCrop()
                .into(image)
        }
    }

    override fun getLayout(): Int = R.layout.item_photo

    override fun initializeViewBinding(view: View): ItemPhotoBinding {
        return ItemPhotoBinding.bind(view)
    }
}