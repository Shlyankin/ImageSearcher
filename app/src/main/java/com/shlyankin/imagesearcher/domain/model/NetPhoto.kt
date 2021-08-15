package com.shlyankin.imagesearcher.domain.model

import com.google.gson.annotations.SerializedName
import java.util.*

open class NetPhoto(
    val id: String,
    @SerializedName("created_at")
    val createdAt: Date,
    val description: String?,
    val urls: PhotoUrls,
    val user: User
)