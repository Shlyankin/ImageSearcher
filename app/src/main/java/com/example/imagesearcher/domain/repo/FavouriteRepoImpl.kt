package com.example.imagesearcher.domain.repo

import com.example.imagesearcher.domain.adapter.PhotoAdapter
import com.example.imagesearcher.domain.dao.FavouritePhotoDao
import com.example.imagesearcher.domain.model.FavouritePhoto
import com.example.imagesearcher.domain.model.PhotoEntity
import kotlinx.coroutines.flow.Flow

class FavouriteRepoImpl(
    private val favouritePhotoDao: FavouritePhotoDao,
    private val photoAdapter: PhotoAdapter
) : FavouriteRepo {
    
    override suspend fun addToFavourite(photo: PhotoEntity) {
        // todo: save photo local
        favouritePhotoDao.insertReplace(photoAdapter.convertFromPhotoToFavouritePhoto(photo, ""))
    }

    override suspend fun deleteFromFavourite(photoId: String) {
        favouritePhotoDao.deleteById(photoId)
    }

    override fun getAll(): Flow<List<FavouritePhoto>> = favouritePhotoDao.getAll()
}