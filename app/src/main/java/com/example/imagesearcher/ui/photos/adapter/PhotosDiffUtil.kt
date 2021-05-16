package com.example.imagesearcher.ui.photos.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.imagesearcher.domain.model.ui.UiPhoto

class PhotosDiffUtil : DiffUtil.ItemCallback<UiPhoto>() {
    override fun areItemsTheSame(oldItem: UiPhoto, newItem: UiPhoto) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UiPhoto, newItem: UiPhoto) = oldItem == newItem
}