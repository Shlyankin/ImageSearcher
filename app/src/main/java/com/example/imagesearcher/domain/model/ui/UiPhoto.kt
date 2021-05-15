package com.example.imagesearcher.domain.model.ui

import com.example.imagesearcher.domain.model.PhotoUrls
import com.example.imagesearcher.domain.model.User
import java.util.*

data class UiPhoto(
    val createdAt: Date,
    val description: String?,
    val urls: PhotoUrls,
    val user: User,
    var isFavourite: Boolean = false
)