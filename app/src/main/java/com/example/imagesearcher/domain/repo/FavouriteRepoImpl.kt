package com.example.imagesearcher.domain.repo

import android.util.Log
import com.example.imagesearcher.domain.adapter.PhotoAdapter
import com.example.imagesearcher.domain.dao.FavouritePhotoDao
import com.example.imagesearcher.domain.model.FavouritePhoto
import com.example.imagesearcher.domain.model.PhotoEntity
import com.example.imagesearcher.manager.FileManager
import kotlinx.coroutines.flow.Flow
import java.io.File

class FavouriteRepoImpl(
    private val favouritePhotoDao: FavouritePhotoDao,
    private val photoAdapter: PhotoAdapter,
    private val fileManager: FileManager,
) : FavouriteRepo {

    companion object {
        const val APP_PHOTO_PREFIX = "ImageSearcher_"
        const val PNG_EXT = ".png"
    }

    override suspend fun addToFavourite(photo: PhotoEntity) {
        val filename = "$APP_PHOTO_PREFIX${photo.user.name}${photo.id}$PNG_EXT"
        val localPath = fileManager.downloadFile(photo.urls.full, filename).path
        favouritePhotoDao.insertReplace(
            photoAdapter.convertFromPhotoToFavouritePhoto(
                photo,
                localPath
            )
        )
    }

    override suspend fun deleteFromFavourite(photo: PhotoEntity) {
        fileManager.stopDownloadFile(photo.urls.full)
        favouritePhotoDao.getSuspend(photo.id)?.let {
            try {
                File(it.localPath).delete()
            } catch (e: Exception) {
                Log.e(
                    FavouriteRepoImpl::class.java.name,
                    "deleteFromFavourite Exception ${e.message}"
                )
            }
        }
        favouritePhotoDao.deleteById(photo.id)
    }

    override fun getAll(): Flow<List<FavouritePhoto>> {
//        GlobalScope.launch {
//            val t = favouritePhotoDao.getAllSusp()
//        }
        return favouritePhotoDao.getAll()
    }
}