package com.example.imagesearcher.domain.usecase

import com.example.imagesearcher.domain.model.FavouritePhoto
import com.example.imagesearcher.domain.model.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface FavouriteUseCase {
    val favouritePhotos: Flow<List<FavouritePhoto>>
    suspend fun deleteFromFavourites(photo: PhotoEntity)
    suspend fun addToFavourite(photo: PhotoEntity)
}