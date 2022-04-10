package com.shlyankin.photos.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import com.shlyankin.navigation.AppScreen
import com.shlyankin.navigation.events.NavigationEventEmitter
import com.shlyankin.photos.model.UiPhoto
import com.shlyankin.photos.ui.usecase.favourite.FavouriteUseCase
import com.shlyankin.photos.ui.usecase.photo.PhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
internal class PhotosViewModelImpl @Inject constructor(
    photosUseCase: PhotosUseCase,
    private val navigationEventEmitter: NavigationEventEmitter,
    private val favouriteUseCase: FavouriteUseCase,
) : ViewModel(), PhotosViewModel {

    private val compositeDisposable = CompositeDisposable()

    private val logger: Logger = Logger.getLogger(PhotosViewModelImpl::class.java.name)

    override val photos = Flowable.combineLatest(
        favouriteUseCase.favouritePhotos.toFlowable(BackpressureStrategy.LATEST),
        photosUseCase.photos.cachedIn(viewModelScope)
    ) { favourite, allPagingData ->
        logger.info("favourite: $favourite, all: $allPagingData")
        allPagingData.map { photo ->
            photo.copy(isFavourite = favourite.find { it.id == photo.id } != null)
        }
    }.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun onPhotoClicked(uiPhoto: UiPhoto) {
        navigationEventEmitter.navigateTo(AppScreen.ViewPhoto(uiPhoto.id))
    }

    override fun addToFavouriteClicked(uiPhoto: UiPhoto) {
        favouriteUseCase.favouritePhotos
            .first(emptyList())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess { favourites ->
                favourites.find { it.id == uiPhoto.id }?.let {
                    favouriteUseCase.removeFromFavourite(it.id)
                } ?: favouriteUseCase.addToFavourite(uiPhoto)
            }
            .subscribe()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}
