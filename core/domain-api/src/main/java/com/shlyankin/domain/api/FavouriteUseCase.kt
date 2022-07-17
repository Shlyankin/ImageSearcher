package com.shlyankin.domain.api

import com.shlyankin.domain.models.FavouritePhotoDomain
import com.shlyankin.domain.models.PhotoDomain
import kotlinx.coroutines.flow.Flow

interface FavouriteUseCase {

    val favouritePhotos: Flow<List<FavouritePhotoDomain>>

    suspend fun removeFromFavourite(id: String)

    suspend fun addToFavourite(photo: PhotoDomain)
}