package com.shlyankin.imagesearcher.domain.adapter

import com.shlyankin.imagesearcher.domain.model.FavouritePhoto
import com.shlyankin.imagesearcher.domain.model.PhotoEntity
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto

class PhotoMapper {

    fun convertFromPhotoToUiPhoto(
        photo: PhotoEntity,
        localPath: String?,
        isFavourite: Boolean = false
    ): UiPhoto =
        UiPhoto(
            photo.id,
            photo.createdAt,
            photo.description,
            photo.urls,
            photo.user,
            localPath,
            isFavourite
        )

    fun convertFromPhotoToUiPhoto(
        photos: List<PhotoEntity>,
        favouriteList: List<FavouritePhoto> = emptyList()
    ): List<UiPhoto> =
        photos.map { photo ->
            val favouritePhoto = favouriteList.find { it == photo }
            convertFromPhotoToUiPhoto(photo, favouritePhoto?.localPath, photo in favouriteList)
        }

    fun convertFromPhotoToUiPhoto(
        photo: PhotoEntity,
        favouriteList: List<FavouritePhoto> = emptyList()
    ): UiPhoto {
        val favouritePhoto = favouriteList.find { it == photo }
        return convertFromPhotoToUiPhoto(photo, favouritePhoto?.localPath, photo in favouriteList)
    }

    fun convertFromFavouritePhotoToUiPhoto(photo: FavouritePhoto): UiPhoto =
        UiPhoto(
            photo.id,
            photo.createdAt,
            photo.description,
            photo.urls,
            photo.user,
            localPath = photo.localPath,
            isFavourite = true
        )

    fun convertFromFavouritePhotoToUiPhoto(photo: List<FavouritePhoto>): List<UiPhoto> =
        photo.map {
            convertFromFavouritePhotoToUiPhoto(it)
        }

    fun convertFromPhotoToFavouritePhoto(photo: PhotoEntity, localName: String): FavouritePhoto =
        FavouritePhoto(
            photo.id,
            photo.createdAt,
            photo.description,
            photo.urls,
            photo.user,
            localName
        )

}