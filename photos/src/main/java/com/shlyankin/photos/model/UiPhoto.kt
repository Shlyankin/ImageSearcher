package com.shlyankin.photos.model

import com.shlyankin.db.database.model.PhotoUrls
import com.shlyankin.db.database.model.User
import java.util.*

internal data class UiPhoto(
    val id: String,
    val createdAt: Date,
    val description: String?,
    val urls: PhotoUrls,
    val user: User,
    var localPath: String?,
    var isFavourite: Boolean = false
)