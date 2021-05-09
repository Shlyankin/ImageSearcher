package com.example.imagesearcher.domain.repo

import com.example.imagesearcher.domain.model.PhotoEntity

interface PhotoRepo {
    suspend fun getPhotos(page: Int): List<PhotoEntity>
}