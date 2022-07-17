package com.shlyankin.photos.ui.photos

import androidx.paging.PagingData
import com.shlyankin.photos.model.PresPhoto
import kotlinx.coroutines.flow.Flow

internal interface PhotosViewModel {

    val photos: Flow<PagingData<PresPhoto>>

    fun onPhotoClicked(uiPhoto: PresPhoto)

    fun addToFavouriteClicked(uiPhoto: PresPhoto)
}