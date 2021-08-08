package com.shlyankin.imagesearcher.domain.repo.photo

import com.shlyankin.imagesearcher.domain.model.PhotoEntity
import com.shlyankin.imagesearcher.domain.net.ResultWrapper

interface PhotoRepo {
    suspend fun getPhoto(photoId: String): PhotoEntity?
    suspend fun getPhotos(page: Int): ResultWrapper<List<PhotoEntity>>
}