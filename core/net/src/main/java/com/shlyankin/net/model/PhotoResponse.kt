package com.shlyankin.net.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class PhotoResponse(
    val id: String,
    @SerializedName("created_at")
    val createdAt: Date,
    val description: String?,
    val urls: PhotoUrls,
    val user: User,
)