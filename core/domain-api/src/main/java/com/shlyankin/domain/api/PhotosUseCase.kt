package com.shlyankin.domain.api

import androidx.paging.PagingData
import com.shlyankin.domain.models.PhotoDomain
import kotlinx.coroutines.flow.Flow

interface PhotosUseCase {

    val photos: Flow<PagingData<PhotoDomain>>
}