package com.shlyankin.imagesearcher.domain.repo.photo

import com.shlyankin.imagesearcher.domain.model.NetPhoto
import com.shlyankin.imagesearcher.domain.net.ResultWrapper
import com.shlyankin.imagesearcher.domain.net.UnsplashApi
import com.shlyankin.imagesearcher.domain.net.safeApiCall

class PhotoRepoImpl(
    private val unsplashApi: UnsplashApi
) : PhotoRepo {

    override suspend fun getPhotos(page: Int): ResultWrapper<List<NetPhoto>> {
        return safeApiCall {
            unsplashApi.randomPhotos(page)
        }
    }
}