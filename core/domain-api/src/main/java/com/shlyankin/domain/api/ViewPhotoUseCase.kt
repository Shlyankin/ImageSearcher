package com.shlyankin.domain.api

import com.shlyankin.domain.models.PhotoDomain
import kotlinx.coroutines.flow.Flow

interface ViewPhotoUseCase {

    suspend fun getPhoto(photoId: String): Flow<PhotoDomain?>

    suspend fun addToFavourite(photoDomain: PhotoDomain)

    suspend fun removeFromFavourite(photoDomain: PhotoDomain)
}