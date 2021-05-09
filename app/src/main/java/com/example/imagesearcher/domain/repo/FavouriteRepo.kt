package com.example.imagesearcher.domain.repo

import com.example.imagesearcher.domain.model.FavouritePhoto
import com.example.imagesearcher.domain.model.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface FavouriteRepo {

    suspend fun addToFavourite(photo: PhotoEntity)

    fun getAll(): Flow<List<FavouritePhoto>>

    suspend fun deleteFromFavourite(photoId: String)
}