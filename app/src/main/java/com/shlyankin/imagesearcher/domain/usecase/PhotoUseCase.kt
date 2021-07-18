package com.shlyankin.imagesearcher.domain.usecase

import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto
import kotlinx.coroutines.flow.Flow

interface PhotoUseCase {
    val photos: Flow<List<UiPhoto>>

    suspend fun updatePhotos(page: Int)
}