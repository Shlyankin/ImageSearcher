package com.shlyankin.imagesearcher.domain.usecase.favourite

import com.shlyankin.imagesearcher.domain.adapter.PhotoMapper
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto
import com.shlyankin.imagesearcher.domain.repo.favourite.FavouriteRepo
import kotlinx.coroutines.flow.map

class FavouriteUseCaseImpl(
    private val favouriteRepo: FavouriteRepo,
    private val photosMapper: PhotoMapper
) : FavouriteUseCase {

    private val rawFavouritePhotos = favouriteRepo.getAll()
    override val favouritePhotos = rawFavouritePhotos.map {
        photosMapper.convertFromFavouritePhotoToUiPhoto(it).reversed()
    }

    override suspend fun addToFavourite(photo: UiPhoto) {
        favouriteRepo.addToFavourite(photosMapper.convertFromUiPhoto(photo))
    }

    override suspend fun removeFromFavourite(id: String) {
        favouriteRepo.get(id)?.let {
            favouriteRepo.deleteFromFavourite(it)
        }
    }
}