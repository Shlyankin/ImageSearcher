package com.shlyankin.myapplication.repo.favourite

import android.util.Log
import com.shlyankin.myapplication.database.dao.FavouritePhotoDao
import com.shlyankin.myapplication.database.model.FavouritePhoto
import com.shlyankin.myapplication.net.file.FileManager
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File

internal class FavouriteRepoImpl(
    private val favouritePhotoDao: FavouritePhotoDao,
    private val fileManager: FileManager,
) : FavouriteRepo {

    companion object {
        const val APP_PHOTO_PREFIX = "ImageSearcher_"
        const val JPG_EXT = ".jpg"
    }

    override fun addToFavourite(photo: FavouritePhoto): Completable {
        val filename = "$APP_PHOTO_PREFIX${photo.user.name}${photo.id}$JPG_EXT"
        val localPath = fileManager.downloadFile(photo.urls.full, filename).path
        return favouritePhotoDao.insertReplace(photo.copy(localPath = localPath))
            .subscribeOn(Schedulers.io())
    }

    override fun deleteFromFavourite(photoId: String) {
        favouritePhotoDao.get(photoId)
            .subscribeOn(Schedulers.io())
            .doOnSuccess { photo ->
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
            .subscribeOn(Schedulers.io())
    }

    override fun deleteFromFavourite(photo: FavouritePhoto): Completable {
        fileManager.stopDownloadFile(photo.urls.full)
        try {
            File(photo.localPath).delete()
        } catch (e: Exception) {
            Log.e(
                FavouriteRepoImpl::class.java.name,
                "deleteFromFavourite Exception ${e.message}"
            )
        }
        return favouritePhotoDao.deleteById(photo.id)
            .subscribeOn(Schedulers.io())
    }

    override fun getAll(): Observable<List<FavouritePhoto>> {
        return favouritePhotoDao.getAll()
            .subscribeOn(Schedulers.io())
    }

    override fun get(id: String): Single<FavouritePhoto> {
        return favouritePhotoDao.get(id)
            .subscribeOn(Schedulers.io())
    }
}