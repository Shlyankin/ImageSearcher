package com.shlyankin.photos.ui.usecase.photo

import androidx.paging.PagingData
import androidx.paging.map
import com.shlyankin.myapplication.datasource.PhotosDataSource
import com.shlyankin.myapplication.net.model.PhotoResponse
import com.shlyankin.photos.mapper.PhotoMapper
import com.shlyankin.photos.model.UiPhoto
import io.reactivex.rxjava3.core.Flowable

internal class PhotosUseCaseImpl(
    photosDataSource: PhotosDataSource,
    photosMapper: PhotoMapper
) : PhotosUseCase {

    override val photos: Flowable<PagingData<UiPhoto>> = photosDataSource.getPhotos()
        .map { value: PagingData<PhotoResponse> ->
            value.map {
                photosMapper.convertFromPhotoToUiPhoto(it)
            }
        }
}