package com.shlyankin.myapplication.datasource

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.shlyankin.myapplication.net.model.PhotoResponse
import com.shlyankin.myapplication.repo.photo.PhotoRepo
import io.reactivex.rxjava3.core.Flowable

class PhotosDataSource(private val repo: PhotoRepo) {

    private companion object {
        const val PAGE_SIZE = 20
    }

    fun getPhotos(): Flowable<PagingData<PhotoResponse>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = ::createPhotosPagingSource
        ).flowable
    }

    private fun createPhotosPagingSource() = PhotosPagingSource(repo)
}