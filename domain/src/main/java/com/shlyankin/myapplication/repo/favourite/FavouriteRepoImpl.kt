package com.shlyankin.myapplication.repo.favourite

import android.util.Log
import com.shlyankin.myapplication.database.dao.FavouritePhotoDao
import com.shlyankin.myapplication.database.model.FavouritePhoto
import com.shlyankin.myapplication.net.file.FileManager
import kotlinx.coroutines.flow.Flow
import java.io.File

internal class FavouriteRepoImpl(
    private val favouritePhotoDao: FavouritePhotoDao,
    private val fileManager: FileManager
) : FavouriteRepo {

    companion object {
        const val APP_PHOTO_PREFIX = "ImageSearcher_"
        const val JPG_EXT = ".jpg"
    }

    override suspend fun addToFavourite(photo: FavouritePhoto) {
        val filename = "$APP_PHOTO_PREFIX${photo.user.name}${photo.id}$JPG_EXT"
        val localPath = fileManager.downloadFile(photo.urls.full, filename).path
        favouritePhotoDao.insertReplace(photo.copy(localPath = localPath))
    }

    override suspend fun deleteFromFavourite(photoId: String) {
        favouritePhotoDao.get(photoId)?.let { photo ->
            fileManager.stopDownloadFile(photo.urls.full)
            try {
                File(photo.localPath).delete()
            } catch (e: Exception) {
                Log.e(
                    FavouriteRepoImpl::class.java.name,
                    "deleteFromFavourite Exception ${e.message}"
                )
            }
        }
        favouritePhotoDao.deleteById(photoId)
    }

    override suspend fun deleteFromFavourite(photo: FavouritePhoto) {
        fileManager.stopDownloadFile(photo.urls.full)
        favouritePhotoDao.get(photo.id)?.let {
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
        return favouritePhotoDao.getAll()
    }

    override suspend fun get(id: String): FavouritePhoto? {
        return favouritePhotoDao.get(id)
    }
}