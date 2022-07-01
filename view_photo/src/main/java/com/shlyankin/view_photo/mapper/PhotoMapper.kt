package com.shlyankin.view_photo.mapper

import com.shlyankin.db.database.model.FavouritePhoto
import com.shlyankin.net.model.PhotoResponse
import com.shlyankin.view_photo.model.UiPhoto
import com.shlyankin.view_photo.model.UiProfile
import java.text.SimpleDateFormat
import java.util.*

internal class PhotoMapper(locale: Locale) {

    private companion object {
        const val USER_FRIENDLY_DATE_FORMAT = "dd MMMM yyyy"
    }

    private val userFriendlyDateFormat = SimpleDateFormat(USER_FRIENDLY_DATE_FORMAT, locale)

    fun convertFromPhotoToUiPhoto(
        photo: com.shlyankin.net.model.PhotoResponse,
        favouriteList: List<FavouritePhoto> = emptyList(),
    ): UiPhoto {
        val favouritePhoto = favouriteList.find { it.id == photo.id }
        return convertFromPhotoToUiPhoto(photo, favouritePhoto?.localPath, favouritePhoto != null)
    }

    private fun convertFromPhotoToUiPhoto(
        photo: com.shlyankin.net.model.PhotoResponse,
        localPath: String?,
        isFavourite: Boolean = false,
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