package com.shlyankin.view_photo.ui.usecase

import com.shlyankin.view_photo.model.UiPhoto
import kotlinx.coroutines.flow.Flow

internal interface ViewPhotoUseCase {

    fun getPhoto(photoId: String): Flow<UiPhoto>

    suspend fun removeFromFavourite(id: String)

    suspend fun addToFavourite(photo: UiPhoto)
}