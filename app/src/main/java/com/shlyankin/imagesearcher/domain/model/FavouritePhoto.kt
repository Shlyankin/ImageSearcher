package com.shlyankin.imagesearcher.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class FavouritePhoto(
    @PrimaryKey
    val id: String,
    val createdAt: Date,
    val description: String?,
    @Embedded
    val urls: PhotoUrls,
    @Embedded(prefix = "user")
    val user: User,
    val localPath: String
)