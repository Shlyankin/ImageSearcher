package com.example.imagesearcher.domain.model

import androidx.room.Entity
import java.util.*

@Entity
class FavouritePhoto(
    id: String,
    createdAt: Date,
    description: String,
    urls: PhotoUrls,
    user: User,
    val localPath: String
) : PhotoEntity(
    id,
    createdAt,
    description,
    urls,
    user
)