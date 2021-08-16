package com.shlyankin.myapplication.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shlyankin.myapplication.net.model.PhotoResponse
import com.shlyankin.myapplication.repo.photo.PhotoRepo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class PhotosPagingSource(
    private val repo: PhotoRepo,
    private val ioDispatcher: CoroutineDispatcher
) : PagingSource<Int, PhotoResponse>() {

    private class DataSourceException(message: String) : Exception(message) {
        constructor() : this("")
    }

    private companion object {
        const val INITIAL_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoResponse> {
        return withContext(ioDispatcher) {
            val key = params.key ?: INITIAL_PAGE
            var result: LoadResult<Int, PhotoResponse>? = null
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