package com.shlyankin.imagesearcher.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.map
import com.shlyankin.imagesearcher.di.IoDispatcher
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto
import com.shlyankin.imagesearcher.domain.usecase.favourite.FavouriteUseCase
import com.shlyankin.imagesearcher.domain.usecase.photo.PhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    photosUseCase: PhotosUseCase,
    private val favouriteUseCase: FavouriteUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {


    private val _favouritePhotos = favouriteUseCase.favouritePhotos
    private val _photos = photosUseCase.photos

    val photos = _favouritePhotos.combine(_photos) { favourite, allPagingData ->
        allPagingData.map { photo ->
            photo.copy(isFavourite = favourite.find { it.id == photo.id } != null)
        }
    }.flowOn(ioDispatcher)

    fun addToFavouriteClicked(uiPhoto: UiPhoto) {
        viewModelScope.launch(ioDispatcher) {
            favouriteUseCase.changePhotoFavouriteState(uiPhoto)
        }
    }

    fun onLoadStateChanged(loadStates: CombinedLoadStates) {
        if (loadStates.refresh is LoadState.Loading) {

        } else {

        }
    }
}