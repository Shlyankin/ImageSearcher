package com.shlyankin.view_photo.ui.usecase

import com.shlyankin.view_photo.model.UiPhoto
import kotlinx.coroutines.flow.Flow

internal interface ViewPhotoUseCase {

    val photo: Flow<UiPhoto>

    suspend fun addToFavourite()

    suspend fun removeFromFavourite()

    suspend fun getPhoto(photoId: String)
}