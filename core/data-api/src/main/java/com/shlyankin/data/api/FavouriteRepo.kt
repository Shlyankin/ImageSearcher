package com.shlyankin.data.api

import com.shlyankin.domain.models.FavouritePhotoDomain
import com.shlyankin.domain.models.PhotoDomain
import kotlinx.coroutines.flow.Flow

interface FavouriteRepo {

    suspend fun addToFavourite(photo: PhotoDomain)

    fun getAll(): Flow<List<FavouritePhotoDomain>>

    suspend fun deleteFromFavourite(photoId: String)

    suspend fun deleteFromFavourite(photo: FavouritePhotoDomain)

    suspend fun get(id: String): FavouritePhotoDomain?
}