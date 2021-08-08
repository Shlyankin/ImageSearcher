package com.shlyankin.imagesearcher.domain.usecase.photo

import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto
import kotlinx.coroutines.flow.Flow

interface PhotosUseCase {
    val photos: Flow<List<UiPhoto>>

    suspend fun updatePhotos(page: Int)
}