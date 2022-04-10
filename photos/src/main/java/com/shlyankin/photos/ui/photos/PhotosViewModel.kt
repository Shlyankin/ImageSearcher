package com.shlyankin.photos.ui.photos

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.shlyankin.photos.model.UiPhoto

internal interface PhotosViewModel {

    val photosLiveData: LiveData<PagingData<UiPhoto>>

    fun onPhotoClicked(uiPhoto: UiPhoto)

    fun addToFavouriteClicked(uiPhoto: UiPhoto)
}