package com.example.imagesearcher.domain.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity
open class PhotoEntity(
    @PrimaryKey
    val id: String,
    @SerializedName("created_at")
    val createdAt: Date,
    val description: String?,
    @Embedded
    val urls: PhotoUrls,
    @Embedded(prefix = "user")
    val user: User
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PhotoEntity

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }
}