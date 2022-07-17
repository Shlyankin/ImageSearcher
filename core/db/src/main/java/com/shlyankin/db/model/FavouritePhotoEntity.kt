package com.shlyankin.db.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class FavouritePhotoEntity(
    @PrimaryKey
    val id: String,
    val createdAt: Date,
    val description: String?,
    @Embedded
    val urls: PhotoUrlsEntity,
    @Embedded(prefix = "user")
    val user: UserEntity,
    val localPath: String,
    val addedInFavouriteAt: Date = Date(),
)