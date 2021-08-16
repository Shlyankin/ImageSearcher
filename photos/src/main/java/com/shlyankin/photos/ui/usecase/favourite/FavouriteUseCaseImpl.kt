package com.shlyankin.photos.ui.usecase.favourite

import com.shlyankin.myapplication.repo.favourite.FavouriteRepo
import com.shlyankin.photos.mapper.PhotoMapper
import com.shlyankin.photos.model.UiPhoto
import kotlinx.coroutines.flow.map

internal class FavouriteUseCaseImpl(
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