package com.shlyankin.myapplication.repo.photo

import com.shlyankin.myapplication.net.UnsplashApi
import com.shlyankin.myapplication.net.model.PhotoResponse
import com.shlyankin.util.net.ResultWrapper
import com.shlyankin.util.net.safeApiCall

internal class PhotoRepoImpl(
    private val unsplashApi: UnsplashApi
) : PhotoRepo {

    override suspend fun getPhoto(photoId: String): ResultWrapper<PhotoResponse> {
        return safeApiCall {
            unsplashApi.getPhoto(photoId)
        }
    }

    override suspend fun getPhotos(page: Int): ResultWrapper<List<PhotoResponse>> {
        return safeApiCall {
            unsplashApi.randomPhotos(page)
        }
    }
}