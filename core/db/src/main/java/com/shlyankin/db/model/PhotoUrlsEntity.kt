package com.shlyankin.db.model

data class PhotoUrlsEntity(
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
)