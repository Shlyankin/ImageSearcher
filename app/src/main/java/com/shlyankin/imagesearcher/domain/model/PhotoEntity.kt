package com.shlyankin.imagesearcher.domain.model

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

        other as PhotoEntity

        if (id != other.id) return false
        if (createdAt != other.createdAt) return false
        if (description != other.description) return false
        if (urls != other.urls) return false
        if (user != other.user) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + urls.hashCode()
        result = 31 * result + user.hashCode()
        return result
    }
}