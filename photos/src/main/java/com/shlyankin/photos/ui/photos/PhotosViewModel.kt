package com.shlyankin.photos.ui.photos

import androidx.paging.PagingData
import com.shlyankin.photos.model.UiPhoto
import io.reactivex.rxjava3.core.Flowable

internal interface PhotosViewModel {

    val photos: Flowable<PagingData<UiPhoto>>

    fun onPhotoClicked(uiPhoto: UiPhoto)

    fun addToFavouriteClicked(uiPhoto: UiPhoto)
}