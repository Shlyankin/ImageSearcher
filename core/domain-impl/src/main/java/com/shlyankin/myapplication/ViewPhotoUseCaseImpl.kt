package com.shlyankin.myapplication

import com.shlyankin.data.api.FavouriteRepo
import com.shlyankin.data.api.PhotoRepo
import com.shlyankin.domain.api.ViewPhotoUseCase
import com.shlyankin.domain.models.PhotoDomain
import kotlinx.coroutines.flow.Flow

class ViewPhotoUseCaseImpl(
    private val photoRepo: PhotoRepo,
    private val favouriteRepo: FavouriteRepo,
) : ViewPhotoUseCase {

    override suspend fun getPhoto(photoId: String): Flow<PhotoDomain?> {
        return photoRepo.getPhoto(photoId)
    }

    override suspend fun addToFavourite(photoDomain: PhotoDomain) {
        favouriteRepo.addToFavourite(photoDomain)
    }

    override suspend fun removeFromFavourite(photoDomain: PhotoDomain) {
        favouriteRepo.deleteFromFavourite(photoDomain.id)
    }
}