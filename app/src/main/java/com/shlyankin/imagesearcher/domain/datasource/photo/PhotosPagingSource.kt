package com.shlyankin.imagesearcher.domain.datasource.photo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shlyankin.imagesearcher.domain.model.NetPhoto
import com.shlyankin.imagesearcher.domain.repo.photo.PhotoRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PhotosPagingSource(
    private val repo: PhotoRepo,
    private val ioDispatcher: CoroutineDispatcher
) : PagingSource<Int, NetPhoto>() {

    private class DataSourceException(message: String) : Exception(message) {
        constructor() : this("")
    }

    private companion object {
        const val INITIAL_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, NetPhoto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, NetPhoto> {
        return withContext(ioDispatcher) {
            val key = params.key ?: INITIAL_PAGE
            var result: LoadResult<Int, NetPhoto>? = null
            repo.getPhotos(key).checkResult(
                onSuccess = {
                    result = LoadResult.Page(
                        data = it,
                        prevKey = if (key == INITIAL_PAGE) null else key - 1,
                        nextKey = key + 1
                    )
                },
                onError = {
                    result = LoadResult.Error(it ?: DataSourceException())
                }
            )
            result ?: throw DataSourceException("Something went wrong")
        }
    }
}