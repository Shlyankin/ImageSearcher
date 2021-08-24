package com.shlyankin.view_photo.ui.usecase

import com.shlyankin.myapplication.repo.favourite.FavouriteRepo
import com.shlyankin.myapplication.repo.photo.PhotoRepo
import com.shlyankin.view_photo.mapper.PhotoMapper
import com.shlyankin.view_photo.model.UiPhoto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class ViewPhotoUseCaseImpl(
    private val photoRepo: PhotoRepo,
    private val favouriteRepo: FavouriteRepo,
    private val photoMapper: PhotoMapper
) : ViewPhotoUseCase {

    override fun getPhoto(photoId: String): Flow<UiPhoto> {
        return flow {
            photoRepo.getPhoto(photoId).checkResult {
                emit(photoMapper.convertFromPhotoToUiPhoto(it))
            }
        }
    }

    override suspend fun addToFavourite(photo: UiPhoto) {
        favouriteRepo.addToFavourite(photoMapper.convertFromUiPhoto(photo))
    }

    override suspend fun removeFromFavourite(id: String) {
        favouriteRepo.get(id)?.let {
            favouriteRepo.deleteFromFavourite(it)
        }
    }
}