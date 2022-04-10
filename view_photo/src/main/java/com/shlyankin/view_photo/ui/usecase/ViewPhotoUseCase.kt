package com.shlyankin.view_photo.ui.usecase

import com.shlyankin.view_photo.model.UiPhoto
import io.reactivex.rxjava3.core.Observable

internal interface ViewPhotoUseCase {

    val photo: Observable<UiPhoto>

    fun addToFavourite()

    fun removeFromFavourite()

    fun getPhoto(photoId: String)
}