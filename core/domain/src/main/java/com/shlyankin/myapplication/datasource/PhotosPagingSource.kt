package com.shlyankin.myapplication.datasource

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.shlyankin.myapplication.net.model.PhotoResponse
import com.shlyankin.myapplication.repo.photo.PhotoRepo
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

internal class PhotosPagingSource(
    private val repo: PhotoRepo,
) : RxPagingSource<Int, PhotoResponse>() {

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

    private fun toLoadResult(key: Int, list: List<PhotoResponse>): LoadResult<Int, PhotoResponse> {
        return LoadResult.Page(
            data = list,
            prevKey = if (key == INITIAL_PAGE) null else key - 1,
            nextKey = key + 1
        )
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, PhotoResponse>> {
        val key = params.key ?: INITIAL_PAGE
        return repo.getPhotos(key)
            .subscribeOn(Schedulers.io())
            .map {
                toLoadResult(key, it)
            }.onErrorReturn {
                return@onErrorReturn LoadResult.Error(it ?: DataSourceException())
            }
    }
}