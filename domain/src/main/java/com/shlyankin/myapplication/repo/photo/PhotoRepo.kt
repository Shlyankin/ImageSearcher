package com.shlyankin.myapplication.repo.photo

import com.shlyankin.myapplication.net.model.PhotoResponse
import com.shlyankin.util.net.ResultWrapper

interface PhotoRepo {

    suspend fun getPhoto(photoId: String): ResultWrapper<PhotoResponse>

    suspend fun getPhotos(page: Int): ResultWrapper<List<PhotoResponse>>
}