package com.shlyankin.photos.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.shlyankin.domain.api.FavouriteUseCase
import com.shlyankin.domain.api.PhotosUseCase
import com.shlyankin.navigation.AppScreen
import com.shlyankin.navigation.events.NavigationEventEmitter
import com.shlyankin.photos.di.IoDispatcher
import com.shlyankin.photos.mapper.toDomain
import com.shlyankin.photos.mapper.toPres
import com.shlyankin.photos.model.PresPhoto
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
    private val navigationEventEmitter: NavigationEventEmitter,
    private val favouriteUseCase: FavouriteUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel(), PhotosViewModel {

    private val logger: Logger = Logger.getLogger(PhotosViewModelImpl::class.java.name)

    private val _favouritePhotos = favouriteUseCase.favouritePhotos
    private val _photos = photosUseCase.photos.cachedIn(viewModelScope)

    override val photos = _favouritePhotos.combine(_photos) { favouriteDomain, allDomain ->
        logger.info("favouriteDomain: $favouriteDomain, all: $allDomain")
        allDomain.map { photo ->
            val isFavourite = favouriteDomain.any { it.id == photo.id }
            photo.toPres().copy(isFavourite = isFavourite)
        }
    }.flowOn(ioDispatcher)

    override fun onPhotoClicked(uiPhoto: PresPhoto) {
        viewModelScope.launch {
            navigationEventEmitter.navigateTo(AppScreen.ViewPhoto(uiPhoto.id))
        }
    }

    override fun addToFavouriteClicked(uiPhoto: PresPhoto) {
        viewModelScope.launch(ioDispatcher) {
            _favouritePhotos.firstOrNull()?.let { favourites ->
                favourites.find { favourite ->
                    favourite.id == uiPhoto.id
                }?.let { favourite ->
                    favouriteUseCase.removeFromFavourite(favourite.id)
                }
            } ?: run {
                favouriteUseCase.addToFavourite(uiPhoto.toDomain())
            }
        }
    }
}
