package com.example.imagesearcher.domain.usecase

import com.example.imagesearcher.domain.model.ui.UiPhoto
import kotlinx.coroutines.flow.Flow

interface FavouriteUseCase {
    val favouritePhotos: Flow<List<UiPhoto>>
    suspend fun changePhotoFavouriteState(photo: UiPhoto)
}