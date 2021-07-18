package com.shlyankin.imagesearcher.ui.photos.adapter

import androidx.recyclerview.widget.DiffUtil
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto

class PhotosDiffUtil : DiffUtil.ItemCallback<UiPhoto>() {
    override fun areItemsTheSame(oldItem: UiPhoto, newItem: UiPhoto) = false

    override fun areContentsTheSame(oldItem: UiPhoto, newItem: UiPhoto) = oldItem == newItem
}