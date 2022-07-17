package com.shlyankin.data.impl.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.shlyankin.net.UnsplashApi
import com.shlyankin.net.model.PhotoResponse
import com.shlyankin.util.net.safeApiCall
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class PhotosPagingSource(
    private val api: UnsplashApi,
    private val ioDispatcher: CoroutineDispatcher,
) : PagingSource<Int, PhotoResponse>() {

    private companion object {
        const val INITIAL_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, PhotoResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)
                ?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoResponse> {
        return withContext(ioDispatcher) {
            val key = params.key ?: INITIAL_PAGE
            var result: LoadResult<Int, PhotoResponse>? = null

            safeApiCall {
                api.randomPhotos(key)
            }.onSuccess { photos ->
                result = createLoadResultPage(key, photos)
            }.onError { throwable ->
                result = createLoadResultError(throwable)
            }

            return@withContext result ?: throw DataSourceException("Something went wrong")
        }
    }

    private fun createLoadResultPage(
        currentKey: Int,
        photos: List<PhotoResponse>,
    ): LoadResult.Page<Int, PhotoResponse> {
        val prevKey = currentKey.minus(1)
            .takeIf { currentKey != INITIAL_PAGE }
        return LoadResult.Page(
            data = photos,
            prevKey = prevKey,
            nextKey = currentKey + 1
        )
    }

    private fun createLoadResultError(
        throwable: Throwable?,
    ): LoadResult.Error<Int, PhotoResponse> {
        return LoadResult.Error(throwable ?: DataSourceException())
    }
}