package com.shlyankin.view_photo.model

import com.shlyankin.myapplication.database.model.PhotoUrls
import com.shlyankin.myapplication.database.model.User
import java.util.*

internal class UiPhoto(
    val id: String,
    val createdAt: Date,
    val description: String?,
    val urls: PhotoUrls,
    val user: User,
    var localPath: String?,
    var isFavourite: Boolean = false
)