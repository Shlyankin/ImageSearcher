package com.shlyankin.imagesearcher.domain.model.ui

import com.shlyankin.imagesearcher.domain.model.PhotoUrls
import com.shlyankin.imagesearcher.domain.model.User
import java.util.*

data class UiPhoto(
    val id: String,
    val createdAt: Date,
    val description: String?,
    val urls: PhotoUrls,
    val user: User,
    var localPath: String?,
    var isFavourite: Boolean = false
)