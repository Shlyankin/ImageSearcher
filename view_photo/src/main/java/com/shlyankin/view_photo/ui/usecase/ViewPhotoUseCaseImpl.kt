package com.shlyankin.view_photo.ui.usecase

import com.shlyankin.myapplication.net.mapper.toEntity
import com.shlyankin.myapplication.net.model.PhotoResponse
import com.shlyankin.myapplication.repo.favourite.FavouriteRepo
import com.shlyankin.myapplication.repo.photo.PhotoRepo
import com.shlyankin.view_photo.mapper.PhotoMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull

internal class ViewPhotoUseCaseImpl(
    private val photoRepo: PhotoRepo,
    private val favouriteRepo: FavouriteRepo,
    private val photoMapper: PhotoMapper
) : ViewPhotoUseCase {

    private val favouritePhotos = favouriteRepo.getAll()
    private val _photoResp = MutableStateFlow<PhotoResponse?>(null)
    override val photo = _photoResp.filterNotNull()
        .combine(favouritePhotos) { photoResponse, favouritePhotos ->
            photoMapper.convertFromPhotoToUiPhoto(photoResponse, favouritePhotos)
        }

    override suspend fun getPhoto(photoId: String) {
        photoRepo.getPhoto(photoId).checkResult { photoResponse ->
            _photoResp.emit(photoResponse)
        }
    }

    override suspend fun addToFavourite() {
        _photoResp.firstOrNull()?.let {
            favouriteRepo.addToFavourite(it.toEntity())
        }
    }

    override suspend fun removeFromFavourite() {
        _photoResp.firstOrNull()?.let {
            favouriteRepo.deleteFromFavourite(it.id)
        }
    }
}