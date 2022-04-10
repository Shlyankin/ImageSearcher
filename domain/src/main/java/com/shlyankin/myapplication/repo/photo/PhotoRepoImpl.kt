package com.shlyankin.myapplication.repo.photo

import com.shlyankin.myapplication.net.UnsplashApi
import com.shlyankin.myapplication.net.model.PhotoResponse
import io.reactivex.rxjava3.core.Single

internal class PhotoRepoImpl(
    private val unsplashApi: UnsplashApi
) : PhotoRepo {

    override fun getPhoto(photoId: String): Single<PhotoResponse> {
        return unsplashApi.getPhoto(photoId)
    }

    override fun getPhotos(page: Int): Single<List<PhotoResponse>> {
        return unsplashApi.randomPhotos(page)
    }
}