package com.shlyankin.data.impl.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shlyankin.net.UnsplashApi
import com.shlyankin.net.model.PhotoResponse
import com.shlyankin.util.net.ResultWrapper
import com.shlyankin.util.net.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class PhotosDataSourceImpl(
    private val api: UnsplashApi,
    private val ioDispatcher: CoroutineDispatcher,
) : PhotosDataSource {

    private companion object {
        const val PAGE_SIZE = 20
    }

    override fun getPhotos(): Flow<PagingData<PhotoResponse>> {
        val config = PagingConfig(pageSize = PAGE_SIZE)
        val pager = Pager(config, pagingSourceFactory = ::createPhotosPagingSource)
        return pager.flow
    }

    private fun createPhotosPagingSource(): PhotosPagingSource {
        return PhotosPagingSource(api, ioDispatcher)
    }

    override suspend fun getPhoto(
        photoId: String,
    ): ResultWrapper<PhotoResponse> {
        return safeApiCall {
            api.getPhoto(photoId)
        }
    }
}