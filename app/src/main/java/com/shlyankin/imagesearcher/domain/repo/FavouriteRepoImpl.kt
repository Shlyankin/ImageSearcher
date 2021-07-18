package com.shlyankin.imagesearcher.domain.repo

import android.util.Log
import com.shlyankin.imagesearcher.domain.adapter.PhotoMapper
import com.shlyankin.imagesearcher.domain.dao.FavouritePhotoDao
import com.shlyankin.imagesearcher.domain.model.FavouritePhoto
import com.shlyankin.imagesearcher.domain.model.PhotoEntity
import com.shlyankin.imagesearcher.manager.FileManager
import kotlinx.coroutines.flow.Flow
import java.io.File

class FavouriteRepoImpl(
    private val favouritePhotoDao: FavouritePhotoDao,
    private val photoMapper: PhotoMapper,
    private val fileManager: FileManager,
) : FavouriteRepo {

    companion object {
        const val APP_PHOTO_PREFIX = "ImageSearcher_"
        const val JPG_EXT = ".jpg"
    }

    override suspend fun addToFavourite(photo: PhotoEntity) {
        val filename = "$APP_PHOTO_PREFIX${photo.user.name}${photo.id}$JPG_EXT"
        val localPath = fileManager.downloadFile(photo.urls.full, filename).path
        favouritePhotoDao.insertReplace(
            photoMapper.convertFromPhotoToFavouritePhoto(
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