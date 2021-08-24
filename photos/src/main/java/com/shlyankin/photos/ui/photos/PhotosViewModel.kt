package com.shlyankin.photos.ui.photos

import androidx.paging.PagingData
import com.shlyankin.photos.model.UiPhoto
import kotlinx.coroutines.flow.Flow

internal interface PhotosViewModel {

    val photos: Flow<PagingData<UiPhoto>>

    fun addToFavouriteClicked(uiPhoto: UiPhoto)
}