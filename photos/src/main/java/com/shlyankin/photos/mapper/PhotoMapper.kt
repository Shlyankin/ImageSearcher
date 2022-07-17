package com.shlyankin.photos.mapper

import com.shlyankin.db.database.model.FavouritePhoto
import com.shlyankin.net.model.PhotoResponse
import com.shlyankin.photos.model.PresPhoto

internal class PhotoMapper {

    fun convertFromPhotoToUiPhoto(
        photo: com.shlyankin.net.model.PhotoResponse,
        favouriteList: List<FavouritePhoto> = emptyList(),
    ): PresPhoto {
        val favouritePhoto = favouriteList.find { it.id == photo.id }
        return convertFromPhotoToUiPhoto(photo, favouritePhoto?.localPath, favouritePhoto != null)
    }

    fun convertFromPhotoToUiPhoto(
        photo: com.shlyankin.net.model.PhotoResponse,
        localPath: String?,
        isFavourite: Boolean = false,
    ): PresPhoto =
        PresPhoto(
            photo.id,
            photo.createdAt,
            photo.description,
            photo.urls,
            photo.user,
            localPath,
            isFavourite
        )

    fun convertFromFavouritePhotoToUiPhoto(photo: List<FavouritePhoto>): List<PresPhoto> =
        photo.map {
            convertFromFavouritePhotoToUiPhoto(it)
        }

    fun convertFromFavouritePhotoToUiPhoto(photo: FavouritePhoto): PresPhoto =
        PresPhoto(
            photo.id,
            photo.createdAt,
            photo.description,
            photo.urls,
            photo.user,
            localPath = photo.localPath,
            isFavourite = true
        )

    fun convertFromUiPhoto(uiPhoto: PresPhoto): FavouritePhoto {
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