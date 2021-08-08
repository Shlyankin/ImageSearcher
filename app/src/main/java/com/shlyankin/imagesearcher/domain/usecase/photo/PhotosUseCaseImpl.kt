package com.shlyankin.imagesearcher.domain.usecase.photo

import com.shlyankin.imagesearcher.domain.adapter.PhotoMapper
import com.shlyankin.imagesearcher.domain.datasource.photo.PhotosDataSource
import com.shlyankin.imagesearcher.domain.model.PhotoEntity
import com.shlyankin.imagesearcher.domain.repo.photo.PhotoRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class PhotosUseCaseImpl(
    private val photosDataSource: PhotosDataSource,
    private val photoRepo: PhotoRepo,
    photosMapper: PhotoMapper
) : PhotosUseCase {

    
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