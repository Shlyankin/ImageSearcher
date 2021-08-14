package com.shlyankin.imagesearcher.domain.repo.photo

import com.shlyankin.imagesearcher.domain.dao.PhotoDao
import com.shlyankin.imagesearcher.domain.model.PhotoEntity
import com.shlyankin.imagesearcher.domain.net.ResultWrapper
import com.shlyankin.imagesearcher.domain.net.UnsplashApi
import com.shlyankin.imagesearcher.domain.net.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher

class PhotoRepoImpl(
    private val unsplashApi: UnsplashApi,
    private val photoDao: PhotoDao,
    private val ioDispatcher: CoroutineDispatcher
) : PhotoRepo {

    override suspend fun getPhoto(photoId: String): PhotoEntity? {
        return photoDao.getPhoto(photoId)
    }

    override suspend fun getPhotos(page: Int): ResultWrapper<List<PhotoEntity>> {
        return safeApiCall {
            unsplashApi.randomPhotos(page)
        }.also {
            it.checkResult {
                photoDao.insertReplace(it)
            }
        }
    }
}