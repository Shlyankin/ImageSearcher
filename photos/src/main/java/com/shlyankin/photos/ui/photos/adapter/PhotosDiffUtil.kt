package com.shlyankin.photos.ui.photos.adapter

import androidx.recyclerview.widget.DiffUtil
import com.shlyankin.photos.model.UiPhoto

internal class PhotosDiffUtil : DiffUtil.ItemCallback<UiPhoto>() {
    override fun areItemsTheSame(oldItem: UiPhoto, newItem: UiPhoto) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: UiPhoto, newItem: UiPhoto) = oldItem == newItem
}