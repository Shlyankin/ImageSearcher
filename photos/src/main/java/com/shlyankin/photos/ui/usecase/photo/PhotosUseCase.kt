package com.shlyankin.photos.ui.usecase.photo

import androidx.paging.PagingData
import com.shlyankin.photos.model.UiPhoto
import io.reactivex.rxjava3.core.Flowable

internal interface PhotosUseCase {
    val photos: Flowable<PagingData<UiPhoto>>
}