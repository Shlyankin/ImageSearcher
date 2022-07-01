package com.shlyankin.myapplication.repo.photo

import com.shlyankin.myapplication.net.model.PhotoResponse
import io.reactivex.rxjava3.core.Single

interface PhotoRepo {

    fun getPhoto(photoId: String): Single<PhotoResponse>

    fun getPhotos(page: Int): Single<List<PhotoResponse>>
}