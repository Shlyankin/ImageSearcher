package com.shlyankin.imagesearcher.domain.usecase.photo

import androidx.paging.PagingData
import androidx.paging.map
import com.shlyankin.imagesearcher.domain.adapter.PhotoMapper
import com.shlyankin.imagesearcher.domain.datasource.photo.PhotosDataSource
import com.shlyankin.imagesearcher.domain.model.NetPhoto
import kotlinx.coroutines.flow.map

class PhotosUseCaseImpl(
    photosDataSource: PhotosDataSource,
    photosMapper: PhotoMapper
) : PhotosUseCase {

    override val photos = photosDataSource.getPhotos().map { value: PagingData<NetPhoto> ->
        value.map {
            photosMapper.convertFromPhotoToUiPhoto(it)
        }
    }
}