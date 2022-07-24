package com.shlyankin.data.api

import com.shlyankin.domain.models.PhotoDomain
import kotlinx.coroutines.flow.Flow

interface FavouriteRepo {

    suspend fun addToFavourite(photo: PhotoDomain)

    fun getAll(): Flow<List<PhotoDomain>>

    suspend fun deleteFromFavourite(photoId: String)

    suspend fun deleteFromFavourite(photo: PhotoDomain)

    suspend fun get(id: String): PhotoDomain?
}