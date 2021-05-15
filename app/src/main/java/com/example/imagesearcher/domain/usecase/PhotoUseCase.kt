package com.example.imagesearcher.domain.usecase

import com.example.imagesearcher.domain.model.PhotoEntity
import kotlinx.coroutines.flow.StateFlow

interface PhotoUseCase {
    val photos: StateFlow<List<PhotoEntity>>

    suspend fun updatePhotos(page: Int)
}