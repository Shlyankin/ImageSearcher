package com.example.imagesearcher.domain.adapter

import com.example.imagesearcher.domain.model.FavouritePhoto
import com.example.imagesearcher.domain.model.PhotoEntity
import com.example.imagesearcher.domain.model.ui.UiPhoto

class PhotoAdapter {

    fun convertFromPhotoToUiPhoto(photo: PhotoEntity, isFavourite: Boolean = false): UiPhoto =
        UiPhoto(photo.createdAt, photo.description, photo.urls, photo.user, isFavourite)

    fun convertFromPhotoToUiPhoto(
        photo: List<PhotoEntity>,
        favouriteList: List<FavouritePhoto> = emptyList()
    ): List<UiPhoto> =
        photo.map {
            UiPhoto(
                it.createdAt,
                it.description,
                it.urls,
                it.user,
                isFavourite = it in favouriteList
            )
        }

    fun convertFromFavouritePhotoToUiPhoto(photo: FavouritePhoto): UiPhoto =
        UiPhoto(photo.createdAt, photo.description, photo.urls, photo.user, isFavourite = true)

    fun convertFromFavouritePhotoToUiPhoto(photo: List<FavouritePhoto>): List<UiPhoto> =
        photo.map { UiPhoto(it.createdAt, it.description, it.urls, it.user, isFavourite = true) }

    fun convertFromPhotoToFavouritePhoto(photo: PhotoEntity, localPath: String): FavouritePhoto =
        FavouritePhoto(
            photo.id,
            photo.createdAt,
            photo.description,
            photo.urls,
            photo.user,
            localPath
        )

}