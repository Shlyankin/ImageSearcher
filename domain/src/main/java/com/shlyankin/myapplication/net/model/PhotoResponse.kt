package com.shlyankin.myapplication.net.model

import com.google.gson.annotations.SerializedName
import com.shlyankin.myapplication.database.model.PhotoUrls
import com.shlyankin.myapplication.database.model.User
import java.util.*

data class PhotoResponse(
    val id: String,
    @SerializedName("created_at")
    val createdAt: Date,
    val description: String?,
    val urls: PhotoUrls,
    val user: User
)