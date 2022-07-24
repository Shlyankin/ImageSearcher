package com.shlyankin.data.impl.repo.favourite

import android.util.Log
import com.shlyankin.data.api.FavouriteRepo
import com.shlyankin.data.impl.mapper.toDomain
import com.shlyankin.data.impl.mapper.toEntity
import com.shlyankin.db.dao.FavouritePhotoDao
import com.shlyankin.domain.models.PhotoDomain
import com.shlyankin.net.file.FileManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.io.File

class FavouriteRepoImpl(
    private val favouritePhotoDao: FavouritePhotoDao,
    private val fileManager: FileManager,
) : FavouriteRepo {

    companion object {
        const val APP_PHOTO_PREFIX = "ImageSearcher_"
        const val JPG_EXT = ".jpg"
    }

    override suspend fun addToFavourite(photo: PhotoDomain) {
        val filename = "$APP_PHOTO_PREFIX${photo.user.name}${photo.id}$JPG_EXT"
        val localPath = fileManager.downloadFile(photo.urls.full, filename).path
        favouritePhotoDao.insertReplace(photo.toEntity(localPath))
    }

    override suspend fun deleteFromFavourite(photo: PhotoDomain) {
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

    override fun getAll(): Flow<List<PhotoDomain>> {
        return favouritePhotoDao.getAll().map { photos ->
            photos.map { photo ->
                photo.toDomain()
            }
        }
    }

    override suspend fun get(id: String): PhotoDomain? {
        return favouritePhotoDao.get(id)?.toDomain()
    }
}