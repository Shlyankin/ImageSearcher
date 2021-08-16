package com.shlyankin.photos.ui.usecase.photo

import androidx.paging.PagingData
import androidx.paging.map
import com.shlyankin.myapplication.datasource.PhotosDataSource
import com.shlyankin.myapplication.net.model.PhotoResponse
import com.shlyankin.photos.mapper.PhotoMapper
import kotlinx.coroutines.flow.map

internal class PhotosUseCaseImpl(
    photosDataSource: PhotosDataSource,
    photosMapper: PhotoMapper
) : PhotosUseCase {

    override val photos = photosDataSource.getPhotos().map { value: PagingData<PhotoResponse> ->
        value.map {
            photosMapper.convertFromPhotoToUiPhoto(it)
        }
    }
}