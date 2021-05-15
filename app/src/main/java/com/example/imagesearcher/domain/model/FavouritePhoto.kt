package com.example.imagesearcher.domain.model

import androidx.room.Entity
import java.util.*

@Entity
class FavouritePhoto(
    id: String,
    createdAt: Date,
    description: String?,
    urls: PhotoUrls,
    user: User,
    val localName: String
) : PhotoEntity(
    id,
    createdAt,
    description,
    urls,
    user
)