package com.shlyankin.imagesearcher.domain.adapter

import com.shlyankin.imagesearcher.domain.model.FavouritePhoto
import com.shlyankin.imagesearcher.domain.model.NetPhoto
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto

class PhotoMapper {

    fun convertFromPhotoToUiPhoto(
        photo: NetPhoto,
        favouriteList: List<FavouritePhoto> = emptyList()
    ): UiPhoto {
        val favouritePhoto = favouriteList.find { it.id == photo.id }
        return convertFromPhotoToUiPhoto(photo, favouritePhoto?.localPath, favouritePhoto != null)
    }

    fun convertFromPhotoToUiPhoto(
        photo: NetPhoto,
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

    fun convertFromFavouritePhotoToUiPhoto(photo: List<FavouritePhoto>): List<UiPhoto> =
        photo.map {
            convertFromFavouritePhotoToUiPhoto(it)
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

    fun convertFromUiPhoto(uiPhoto: UiPhoto): FavouritePhoto {
        return FavouritePhoto(
            uiPhoto.id,
            uiPhoto.createdAt,
            uiPhoto.description,
            uiPhoto.urls,
            uiPhoto.user,
            uiPhoto.localPath ?: ""
        )
    }

}