package com.shlyankin.imagesearcher.domain.datasource.photo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shlyankin.imagesearcher.domain.model.NetPhoto
import com.shlyankin.imagesearcher.domain.repo.photo.PhotoRepo
import kotlinx.coroutines.flow.Flow

class PhotosDataSource(private val repo: PhotoRepo) {

    private companion object {
        const val PAGE_SIZE = 20
    }

    fun getPhotos(): Flow<PagingData<NetPhoto>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = ::createPhotosPagingSource
        ).flow
    }

    private fun createPhotosPagingSource() = PhotosPagingSource(repo)
}