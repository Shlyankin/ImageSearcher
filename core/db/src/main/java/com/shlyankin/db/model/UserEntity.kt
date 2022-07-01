package com.shlyankin.db.model

import androidx.room.Embedded

data class UserEntity(
    val name: String,
    @Embedded
    val profileImage: ProfileImageEntity?,
)