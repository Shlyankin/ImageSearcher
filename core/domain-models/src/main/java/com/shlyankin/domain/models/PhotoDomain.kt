package com.shlyankin.domain.models

import java.util.*

data class PhotoDomain(
    val id: String,
    val createdAt: Date,
    val description: String?,
    val urls: PhotoUrlsDomain,
    val user: UserDomain,
)