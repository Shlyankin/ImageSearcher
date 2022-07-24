package com.shlyankin.view_photo.model

import java.util.*

data class PresPhoto(
    val id: String,
    val createdAt: Date,
    val description: String?,
    val urls: PresPhotoUrls,
    val user: PresUser,
    var localPath: String?,
    var isFavourite: Boolean = false,
)