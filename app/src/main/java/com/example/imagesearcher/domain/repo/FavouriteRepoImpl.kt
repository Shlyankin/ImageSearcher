package com.example.imagesearcher.domain.repo

import com.example.imagesearcher.domain.adapter.PhotoAdapter
import com.example.imagesearcher.domain.dao.FavouritePhotoDao
import com.example.imagesearcher.domain.model.FavouritePhoto
import com.example.imagesearcher.domain.model.PhotoEntity
import com.example.imagesearcher.manager.FileManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch

class FavouriteRepoImpl(
    private val favouritePhotoDao: FavouritePhotoDao,
    private val photoAdapter: PhotoAdapter,
    private val fileManager: FileManager,
) : FavouriteRepo {

    companion object {
        const val APP_PHOTO_PREFIX = "ImageSearcher_"
    }

    override suspend fun addToFavourite(photo: PhotoEntity) {
        val filename = "$APP_PHOTO_PREFIX${photo.user.name}${photo.id}"
        fileManager.downloadFile(photo.urls.full, filename)
        favouritePhotoDao.insertReplace(
            photoAdapter.convertFromPhotoToFavouritePhoto(
                photo,
                filename
            )
        )
    }

    override suspend fun deleteFromFavourite(photo: PhotoEntity) {
        fileManager.stopDownloadFile(photo.urls.full)
        favouritePhotoDao.deleteById(photo.id)
    }

    override fun getAll(): Flow<List<FavouritePhoto>> {
        GlobalScope.launch {
            val t = favouritePhotoDao.getAllSusp()
        }
        return emptyFlow()
    }
}