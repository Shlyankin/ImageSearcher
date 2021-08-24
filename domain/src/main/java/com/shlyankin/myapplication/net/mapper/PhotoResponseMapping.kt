package com.shlyankin.myapplication.net.mapper

import com.shlyankin.myapplication.database.model.FavouritePhoto
import com.shlyankin.myapplication.net.model.PhotoResponse

fun PhotoResponse.toEntity(): FavouritePhoto = FavouritePhoto(
    id, createdAt, description, urls, user, ""
)