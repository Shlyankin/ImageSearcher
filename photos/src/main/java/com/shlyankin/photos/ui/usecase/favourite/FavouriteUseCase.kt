package com.shlyankin.photos.ui.usecase.favourite

import com.shlyankin.photos.model.UiPhoto
import io.reactivex.rxjava3.core.Observable

internal interface FavouriteUseCase {
    val favouritePhotos: Observable<List<UiPhoto>>

    fun removeFromFavourite(id: String)
    fun addToFavourite(photo: UiPhoto)
}