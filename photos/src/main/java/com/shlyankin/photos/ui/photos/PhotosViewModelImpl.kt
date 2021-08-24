package com.shlyankin.photos.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.shlyankin.photos.di.IoDispatcher
import com.shlyankin.photos.model.UiPhoto
import com.shlyankin.photos.ui.usecase.favourite.FavouriteUseCase
import com.shlyankin.photos.ui.usecase.photo.PhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
internal class PhotosViewModelImpl @Inject constructor(
    photosUseCase: PhotosUseCase,
    private val favouriteUseCase: FavouriteUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel(), PhotosViewModel {

    private val logger: Logger = Logger.getLogger(PhotosViewModelImpl::class.java.name)

    private val _favouritePhotos = favouriteUseCase.favouritePhotos
    private val _photos = photosUseCase.photos.cachedIn(viewModelScope)

    override val photos = _favouritePhotos.combine(_photos) { favourite, allPagingData ->
        logger.info("favourite: $favourite, all: $allPagingData")
        allPagingData.map { photo ->
            photo.copy(isFavourite = favourite.find { it.id == photo.id } != null)
        }
    }.flowOn(ioDispatcher)

    override fun addToFavouriteClicked(uiPhoto: UiPhoto) {
        viewModelScope.launch(ioDispatcher) {
            _favouritePhotos.firstOrNull()?.let { favourites ->
                favourites.find { it.id == uiPhoto.id }?.let {
                    favouriteUseCase.removeFromFavourite(it.id)
                }
            } ?: favouriteUseCase.addToFavourite(uiPhoto)
        }
    }
}
