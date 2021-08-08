package com.shlyankin.imagesearcher.domain.datasource.photo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shlyankin.imagesearcher.domain.model.PhotoEntity
import com.shlyankin.imagesearcher.domain.repo.photo.PhotoRepo

class PhotosPagingSource(private val repo: PhotoRepo) : PagingSource<Int, PhotoEntity>() {

    private class DataSourceException(message: String) : Exception(message) {
        constructor() : this("")
    }

    private companion object {
        const val INITIAL_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoEntity> {
        val key = params.key ?: INITIAL_PAGE
        var result: LoadResult<Int, PhotoEntity>? = null
        repo.getPhotos(key).checkResult(
            onSuccess = {
                result = LoadResult.Page(
                    data = it,
                    prevKey = if (key == INITIAL_PAGE) null else key - 1,
                    nextKey = key + 1 //todo: возможно нужно чекнуть, что еще есть че грузить
                )
            },
            onError = {
                result = LoadResult.Error(it ?: DataSourceException())
            }
        )
        return result ?: throw DataSourceException("Something went wrong")
    }
}