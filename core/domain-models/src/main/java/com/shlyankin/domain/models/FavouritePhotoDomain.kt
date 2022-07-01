package com.shlyankin.domain.models

import java.util.*

data class FavouritePhotoDomain(
    val id: String,
    val createdAt: Date,
    val description: String?,
    val urls: PhotoUrlsDomain,
    val user: UserDomain,
    val localPath: String,
)