package com.shlyankin.imagesearcher.domain.repo

import com.shlyankin.imagesearcher.domain.model.FavouritePhoto
import com.shlyankin.imagesearcher.domain.model.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface FavouriteRepo {

    suspend fun addToFavourite(photo: PhotoEntity)

    fun getAll(): Flow<List<FavouritePhoto>>

    suspend fun deleteFromFavourite(photo: PhotoEntity)
}