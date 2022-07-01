package com.shlyankin.data.impl.repo.photo

import androidx.paging.PagingData
import androidx.paging.map
import com.shlyankin.data.impl.datasource.PhotosDataSource
import com.shlyankin.data.impl.mapper.toDomain
import com.shlyankin.domain.models.PhotoDomain
import com.shlyankin.net.model.PhotoResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class PhotoRepoImpl(
    private val photosDataSource: PhotosDataSource,
) : com.shlyankin.data.api.PhotoRepo {

    override fun getPhotos(): Flow<PagingData<PhotoDomain>> {
        return photosDataSource.getPhotos().map { pagingData ->
            pagingData.map { photoResponse ->
                photoResponse.toDomain()
            }
        }
    }

    override suspend fun getPhoto(photoId: String): PhotoDomain? {
        var result: PhotoResponse? = null
        photosDataSource.getPhoto(photoId).onSuccess { photo ->
            result = photo
        }
        return result?.toDomain()
    }
}