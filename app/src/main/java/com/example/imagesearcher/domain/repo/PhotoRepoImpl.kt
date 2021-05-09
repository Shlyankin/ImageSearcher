package com.example.imagesearcher.domain.repo

import com.example.imagesearcher.domain.dao.PhotoDao
import com.example.imagesearcher.domain.model.PhotoEntity
import com.example.imagesearcher.domain.net.UnsplashApi

class PhotoRepoImpl(
    private val unsplashApi: UnsplashApi,
    private val photoDao: PhotoDao
) : PhotoRepo {

    override suspend fun getPhotos(page: Int): List<PhotoEntity> {
        return unsplashApi.searchPhotos("random", page).results
    }
}