package com.example.imagesearcher.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Photo(
    val id: String,
    @SerializedName("created_at")
    val createdAt: Date,
    val description: String,
    @Embedded
    val urls: PhotoUrls,
    @Embedded
    val user: User
)