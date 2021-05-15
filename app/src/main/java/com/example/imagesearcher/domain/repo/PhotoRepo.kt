package com.example.imagesearcher.domain.repo

import com.example.imagesearcher.domain.model.PhotoEntity
import com.example.imagesearcher.domain.net.ResultWrapper

interface PhotoRepo {
    suspend fun getPhotos(page: Int): ResultWrapper<List<PhotoEntity>>
}