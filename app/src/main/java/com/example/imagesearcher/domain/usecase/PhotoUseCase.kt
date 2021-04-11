package com.example.imagesearcher.domain.usecase

import com.example.imagesearcher.domain.model.Photo
import kotlinx.coroutines.flow.Flow

interface PhotoUseCase {
    val photos: Flow<List<Photo>>

    suspend fun updatePhotos(page: Int)
}