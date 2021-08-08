package com.shlyankin.imagesearcher.domain.usecase.favourite

import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto
import kotlinx.coroutines.flow.Flow

interface FavouriteUseCase {
    val favouritePhotos: Flow<List<UiPhoto>>
    suspend fun changePhotoFavouriteState(photo: UiPhoto)
}