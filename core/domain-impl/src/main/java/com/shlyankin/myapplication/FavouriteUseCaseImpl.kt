package com.shlyankin.myapplication

import com.shlyankin.data.api.FavouriteRepo
import com.shlyankin.domain.api.FavouriteUseCase
import com.shlyankin.domain.models.PhotoDomain

class FavouriteUseCaseImpl(
    private val favouriteRepo: FavouriteRepo,
) : FavouriteUseCase {

    override val favouritePhotos = favouriteRepo.getAll()

    override suspend fun addToFavourite(photo: PhotoDomain) {
        favouriteRepo.addToFavourite(photo)
    }

    override suspend fun removeFromFavourite(id: String) {
        favouriteRepo.deleteFromFavourite(id)
    }
}