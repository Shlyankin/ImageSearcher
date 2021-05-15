package com.example.imagesearcher.domain.repo

import com.example.imagesearcher.domain.dao.PhotoDao
import com.example.imagesearcher.domain.model.PhotoEntity
import com.example.imagesearcher.domain.net.ResultWrapper
import com.example.imagesearcher.domain.net.UnsplashApi
import com.example.imagesearcher.domain.net.safeApiCall

class PhotoRepoImpl(
    private val unsplashApi: UnsplashApi,
    private val photoDao: PhotoDao
) : PhotoRepo {

    override suspend fun getPhotos(page: Int): ResultWrapper<List<PhotoEntity>> {
        return safeApiCall {
            unsplashApi.searchPhotos("random", page).results
        }
    }
}