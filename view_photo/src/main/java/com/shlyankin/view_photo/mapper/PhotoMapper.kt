package com.shlyankin.view_photo.mapper

import com.shlyankin.myapplication.database.model.FavouritePhoto
import com.shlyankin.myapplication.net.model.PhotoResponse
import com.shlyankin.view_photo.model.UiPhoto
import com.shlyankin.view_photo.model.UiProfile
import java.text.SimpleDateFormat
import java.util.*

internal class PhotoMapper {

    private val userFriendlyDateFormat = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))

    fun convertFromPhotoToUiPhoto(
        photo: PhotoResponse,
        favouriteList: List<FavouritePhoto> = emptyList()
    ): UiPhoto {
        val favouritePhoto = favouriteList.find { it.id == photo.id }
        return convertFromPhotoToUiPhoto(photo, favouritePhoto?.localPath, favouritePhoto != null)
    }

    private fun convertFromPhotoToUiPhoto(
        photo: PhotoResponse,
        localPath: String?,
        isFavourite: Boolean = false
    ): UiPhoto =
        UiPhoto(
            photo.id,
            userFriendlyDateFormat.format(photo.createdAt),
            photo.description,
            photo.urls.full,
            UiProfile(photo.user.name, photo.user.profileImage?.small),
            localPath,
            isFavourite
        )

}