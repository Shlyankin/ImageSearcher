package com.example.imagesearcher.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
data class Photo(
    @PrimaryKey
    val id: String,
    @SerializedName("created_at")
    val createdAt: Date,
    val description: String,
    @Embedded
    val urls: PhotoUrls,
    @Embedded(prefix = "user")
    val user: User
)