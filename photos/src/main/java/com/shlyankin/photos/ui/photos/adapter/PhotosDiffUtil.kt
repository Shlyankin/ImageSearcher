package com.shlyankin.photos.ui.photos.adapter

import androidx.recyclerview.widget.DiffUtil
import com.shlyankin.photos.model.PresPhoto

internal class PhotosDiffUtil : DiffUtil.ItemCallback<PresPhoto>() {
    override fun areItemsTheSame(oldItem: PresPhoto, newItem: PresPhoto) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: PresPhoto, newItem: PresPhoto) = oldItem == newItem
}