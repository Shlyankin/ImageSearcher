package com.shlyankin.photos.ui.usecase.favourite

import com.shlyankin.photos.model.UiPhoto
import kotlinx.coroutines.flow.Flow

internal interface FavouriteUseCase {
    val favouritePhotos: Flow<List<UiPhoto>>

    suspend fun removeFromFavourite(id: String)
    suspend fun addToFavourite(photo: UiPhoto)
}