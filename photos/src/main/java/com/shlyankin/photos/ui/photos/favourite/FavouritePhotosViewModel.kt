package com.shlyankin.photos.ui.photos.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.shlyankin.navigation.AppScreen
import com.shlyankin.navigation.events.NavigationEventEmitter
import com.shlyankin.photos.model.UiPhoto
import com.shlyankin.photos.ui.photos.PhotosViewModel
import com.shlyankin.photos.ui.usecase.favourite.FavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FavouritePhotosViewModel @Inject constructor(
    private val favouriteUseCase: FavouriteUseCase,
    private val navigationEventEmitter: NavigationEventEmitter,
) : ViewModel(), PhotosViewModel {

    override val photos = favouriteUseCase.favouritePhotos
        .map { PagingData.from(it) }
        .toFlowable(BackpressureStrategy.LATEST)
        .cachedIn(viewModelScope)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    override fun onPhotoClicked(uiPhoto: UiPhoto) {
        viewModelScope.launch {
            navigationEventEmitter.navigateTo(AppScreen.ViewPhoto(uiPhoto.id))
        }
    }

    override fun addToFavouriteClicked(uiPhoto: UiPhoto) {
        favouriteUseCase.removeFromFavourite(uiPhoto.id)
    }
}