package com.shlyankin.photos.ui.usecase.photo

import androidx.paging.PagingData
import androidx.paging.map
import com.shlyankin.data.impl.datasource.PhotosDataSourceImpl
import com.shlyankin.net.model.PhotoResponse
import com.shlyankin.photos.mapper.PhotoMapper
import kotlinx.coroutines.flow.map

internal class PhotosUseCaseImpl(
    photosDataSource: PhotosDataSourceImpl,
    photosMapper: PhotoMapper,
) : PhotosUseCase {

    override val photos = photosDataSource.getPhotos()
        .map { value: PagingData<com.shlyankin.net.model.PhotoResponse> ->
            value.map {
                photosMapper.convertFromPhotoToUiPhoto(it)
            }
        }
}