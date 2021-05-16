package com.example.imagesearcher.domain.model

import androidx.room.Embedded
import androidx.room.PrimaryKey
import java.util.*

abstract class Photo(
    @PrimaryKey
    open val id: String,
    open val createdAt: Date,
    open val description: String?,
    @Embedded open val urls: PhotoUrls,
    @Embedded
    open val user: User
)