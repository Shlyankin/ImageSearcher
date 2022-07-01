package com.shlyankin.net.model

import androidx.room.Embedded

data class User(
    val name: String,
    @Embedded
    val profileImage: ProfileImage?,
)