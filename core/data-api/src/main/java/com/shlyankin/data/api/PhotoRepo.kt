package com.shlyankin.data.api

import androidx.paging.PagingData
import com.shlyankin.domain.models.PhotoDomain
import kotlinx.coroutines.flow.Flow

interface PhotoRepo {

    suspend fun getPhoto(photoId: String): PhotoDomain?

    fun getPhotos(): Flow<PagingData<PhotoDomain>>
}