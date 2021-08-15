package com.shlyankin.imagesearcher.domain.usecase.photo

import androidx.paging.PagingData
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto
import kotlinx.coroutines.flow.Flow

interface PhotosUseCase {
    val photos: Flow<PagingData<UiPhoto>>
}