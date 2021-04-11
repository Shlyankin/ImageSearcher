package com.example.imagesearcher.domain.repo

import com.example.imagesearcher.domain.model.Photo

interface PhotoRepo {
    suspend fun getPhotos(page: Int): List<Photo>
}