package com.shlyankin.view_photo.model

internal class UiPhoto(
    val id: String,
    val createdAt: String,
    val description: String?,
    val url: String,
    val user: UiProfile,
    var localPath: String?,
    var isFavourite: Boolean = false
)