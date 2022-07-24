package com.shlyankin.data.impl.repo.photo

import androidx.paging.PagingData
import androidx.paging.map
import com.shlyankin.data.api.PhotoRepo
import com.shlyankin.data.impl.datasource.PhotosDataSource
import com.shlyankin.data.impl.mapper.toDomain
import com.shlyankin.db.dao.FavouritePhotoDao
import com.shlyankin.domain.models.PhotoDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PhotoRepoImpl(
    private val photosDataSource: PhotosDataSource,
    private val favouritePhotoDao: FavouritePhotoDao,
) : PhotoRepo {

    override fun getPhotos(): Flow<PagingData<PhotoDomain>> {
        return photosDataSource.getPhotos().map { pagingData ->
            pagingData.map { photoResponse ->
                photoResponse.toDomain()
            }
        }
    }

    override suspend fun getPhoto(
        photoId: String,
    ): Flow<PhotoDomain?> {
        return favouritePhotoDao.getFlow(photoId).map { favourite ->
            favourite?.toDomain() ?: photosDataSource.getPhoto(photoId).takeValue()?.toDomain()
        }
    }
}