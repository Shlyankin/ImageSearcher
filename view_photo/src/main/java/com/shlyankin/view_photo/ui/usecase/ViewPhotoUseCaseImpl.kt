package com.shlyankin.view_photo.ui.usecase

import com.shlyankin.data.api.FavouriteRepo
import com.shlyankin.data.api.PhotoRepo
import com.shlyankin.net.mapper.toEntity
import com.shlyankin.net.model.PhotoResponse
import com.shlyankin.view_photo.mapper.PhotoMapper
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull

internal class ViewPhotoUseCaseImpl(
    private val photoRepo: com.shlyankin.data.api.PhotoRepo,
    private val favouriteRepo: com.shlyankin.data.api.FavouriteRepo,
    private val photoMapper: PhotoMapper,
) : ViewPhotoUseCase {

    private val favouritePhotos = favouriteRepo.getAll()
    private val _photoResp = MutableStateFlow<com.shlyankin.net.model.PhotoResponse?>(null)
    override val photo = _photoResp.filterNotNull()
        .combine(favouritePhotos) { photoResponse, favouritePhotos ->
            photoMapper.convertFromPhotoToUiPhoto(photoResponse, favouritePhotos)
        }

    override suspend fun getPhoto(photoId: String) {
        _photoResp.emit(photoRepo.getPhoto(photoId))
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