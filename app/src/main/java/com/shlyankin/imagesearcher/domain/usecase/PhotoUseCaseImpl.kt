package com.shlyankin.imagesearcher.domain.usecase

import com.shlyankin.imagesearcher.domain.adapter.PhotoMapper
import com.shlyankin.imagesearcher.domain.model.PhotoEntity
import com.shlyankin.imagesearcher.domain.repo.PhotoRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class PhotoUseCaseImpl(
    private val photoRepo: PhotoRepo,
    photosMapper: PhotoMapper
) : PhotoUseCase {

    private val rawPhotos = MutableStateFlow<List<PhotoEntity>>(emptyList())
    override val photos = rawPhotos.map {
        photosMapper.convertFromPhotoToUiPhoto(it)
    }

    override suspend fun updatePhotos(page: Int) {
        photoRepo.getPhotos(page).checkResult {
            rawPhotos.emit(it)
        }
    }
}