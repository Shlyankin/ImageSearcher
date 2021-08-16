package com.shlyankin.photos.ui.usecase.photo

import androidx.paging.PagingData
import com.shlyankin.photos.model.UiPhoto
import kotlinx.coroutines.flow.Flow

internal interface PhotosUseCase {
    val photos: Flow<PagingData<UiPhoto>>
}