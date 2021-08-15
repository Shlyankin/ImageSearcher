package com.shlyankin.imagesearcher.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.cachedIn
import androidx.paging.map
import com.shlyankin.imagesearcher.di.IoDispatcher
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto
import com.shlyankin.imagesearcher.domain.usecase.favourite.FavouriteUseCase
import com.shlyankin.imagesearcher.domain.usecase.photo.PhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    photosUseCase: PhotosUseCase,
    private val favouriteUseCase: FavouriteUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val logger: Logger = Logger.getLogger(PhotosViewModel::class.java.name)

    private val _currentState = MutableSharedFlow<PhotoUiState>()
    val currentState =
        _currentState.buffer(capacity = 1, onBufferOverflow = BufferOverflow.DROP_OLDEST)

    private val _favouritePhotos = favouriteUseCase.favouritePhotos
    private val _photos = photosUseCase.photos.cachedIn(viewModelScope)

    val photos = _favouritePhotos.combine(_photos) { favourite, allPagingData ->
        logger.info("favourite: $favourite, all: $allPagingData")
        allPagingData.map { photo ->
            photo.copy(isFavourite = favourite.find { it.id == photo.id } != null)
        }
    }.flowOn(ioDispatcher)

    fun addToFavouriteClicked(uiPhoto: UiPhoto) {
        viewModelScope.launch(ioDispatcher) {
            _favouritePhotos.firstOrNull()?.let { favourites ->
                favourites.find { it.id == uiPhoto.id }?.let {
                    favouriteUseCase.removeFromFavourite(it.id)
                }
            } ?: favouriteUseCase.addToFavourite(uiPhoto)
        }
    }

    fun onLoadStateChanged(loadState: CombinedLoadStates) {
        viewModelScope.launch {
            if (loadState.refresh is LoadState.Loading) {
                _currentState.emit(PhotoUiState(true, false, false))
            } else {
                val errorState = when {
                    loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                    loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                    loadState.refresh is LoadState.Error -> {
                        loadState.refresh as LoadState.Error
                    }
                    else -> null
                }
                _currentState.emit(
                    PhotoUiState(
                        false,
                        loadState.refresh is LoadState.Error,
                        errorState != null
                    )
                )
            }
        }
    }
}

data class PhotoUiState(
    val progressVisible: Boolean,
    val retryVisible: Boolean,
    val errorVisible: Boolean
)