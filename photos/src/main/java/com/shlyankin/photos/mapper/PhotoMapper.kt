package com.shlyankin.photos.mapper

import com.shlyankin.db.database.model.FavouritePhoto
import com.shlyankin.net.model.PhotoResponse
import com.shlyankin.photos.model.UiPhoto

internal class PhotoMapper {

    fun convertFromPhotoToUiPhoto(
        photo: com.shlyankin.net.model.PhotoResponse,
        favouriteList: List<FavouritePhoto> = emptyList(),
    ): UiPhoto {
        val favouritePhoto = favouriteList.find { it.id == photo.id }
        return convertFromPhotoToUiPhoto(photo, favouritePhoto?.localPath, favouritePhoto != null)
    }

    fun convertFromPhotoToUiPhoto(
        photo: com.shlyankin.net.model.PhotoResponse,
        localPath: String?,
        isFavourite: Boolean = false,
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