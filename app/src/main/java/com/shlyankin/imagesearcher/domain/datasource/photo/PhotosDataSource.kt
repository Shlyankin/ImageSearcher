package com.shlyankin.imagesearcher.domain.datasource.photo

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.shlyankin.imagesearcher.domain.model.PhotoEntity
import com.shlyankin.imagesearcher.domain.repo.photo.PhotoRepo
import kotlinx.coroutines.flow.Flow

class PhotosDataSource(private val repo: PhotoRepo) {
    fun getPhotos(): Flow<PagingData<PhotoEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 1,
                enablePlaceholders = false
            ),
            pagingSourceFactory = ::createPhotosPagingSource
        ).flow
    }

    private fun createPhotosPagingSource() = PhotosPagingSource(repo)
}