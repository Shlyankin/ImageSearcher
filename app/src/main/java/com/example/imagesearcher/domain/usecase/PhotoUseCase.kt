package com.example.imagesearcher.domain.usecase

import com.example.imagesearcher.domain.model.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface PhotoUseCase {
    val photos: Flow<List<PhotoEntity>>

    suspend fun updatePhotos(page: Int)
}