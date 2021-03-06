package com.shlyankin.myapplication.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shlyankin.myapplication.net.model.PhotoResponse
import com.shlyankin.myapplication.repo.photo.PhotoRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow

class PhotosDataSource(private val repo: PhotoRepo, private val ioDispatcher: CoroutineDispatcher) {

    private companion object {
        const val PAGE_SIZE = 20
    }

    fun getPhotos(): Flow<PagingData<PhotoResponse>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = ::createPhotosPagingSource
        ).flow
    }

    private fun createPhotosPagingSource() = PhotosPagingSource(repo, ioDispatcher)
}