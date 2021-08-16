package com.shlyankin.myapplication.database.model

import androidx.room.Embedded

data class User(
    val name: String,
    @Embedded
    val profileImage: ProfileImage?
)