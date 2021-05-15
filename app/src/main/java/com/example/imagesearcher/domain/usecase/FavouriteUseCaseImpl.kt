package com.example.imagesearcher.domain.usecase

import com.example.imagesearcher.domain.model.PhotoEntity
import com.example.imagesearcher.domain.repo.FavouriteRepo

class FavouriteUseCaseImpl(
    private val favouriteRepo: FavouriteRepo
) : FavouriteUseCase {

    override val favouritePhotos = favouriteRepo.getAll()

    override suspend fun deleteFromFavourites(photo: PhotoEntity) {
        favouriteRepo.deleteFromFavourite(photo)
    }

    override suspend fun addToFavourite(photo: PhotoEntity) {
        favouriteRepo.addToFavourite(photo)
    }
}