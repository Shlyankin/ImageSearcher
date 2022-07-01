package com.shlyankin.data.impl.datasource

import androidx.paging.PagingData
import com.shlyankin.net.model.PhotoResponse
import com.shlyankin.util.net.ResultWrapper
import kotlinx.coroutines.flow.Flow

interface PhotosDataSource {

    fun getPhotos(): Flow<PagingData<PhotoResponse>>

    suspend fun getPhoto(photoId: String): ResultWrapper<PhotoResponse>
}