package com.example.imagesearcher.domain.adapter

import com.example.imagesearcher.domain.model.FavouritePhoto
import com.example.imagesearcher.domain.model.PhotoEntity
import com.example.imagesearcher.domain.model.ui.UiPhoto

class PhotoAdapter {

    fun convertFromPhotoToUiPhoto(photo: PhotoEntity): UiPhoto =
        UiPhoto(photo.createdAt, photo.description, photo.urls, photo.user)

    fun convertFromPhotoToUiPhoto(photo: List<PhotoEntity>): List<UiPhoto> =
        photo.map { UiPhoto(it.createdAt, it.description, it.urls, it.user) }

    fun convertFromFavouritePhotoToUiPhoto(photo: FavouritePhoto): UiPhoto =
        UiPhoto(photo.createdAt, photo.description, photo.urls, photo.user)

    fun convertFromFavouritePhotoToUiPhoto(photo: List<FavouritePhoto>): List<UiPhoto> =
        photo.map { UiPhoto(it.createdAt, it.description, it.urls, it.user) }

}