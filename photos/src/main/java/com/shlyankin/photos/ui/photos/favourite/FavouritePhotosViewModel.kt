package com.shlyankin.photos.ui.photos.favourite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.shlyankin.navigation.AppScreen
import com.shlyankin.navigation.events.NavigationEventEmitter
import com.shlyankin.photos.di.IoDispatcher
import com.shlyankin.photos.model.UiPhoto
import com.shlyankin.photos.ui.photos.PhotosViewModel
import com.shlyankin.photos.ui.usecase.favourite.FavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FavouritePhotosViewModel @Inject constructor(
    private val favouriteUseCase: FavouriteUseCase,
    private val navigationEventEmitter: NavigationEventEmitter,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel(), PhotosViewModel {

    override val photos: Flow<PagingData<UiPhoto>> = favouriteUseCase.favouritePhotos
        .map { PagingData.from(it) }.flowOn(ioDispatcher)

    override fun onPhotoClicked(uiPhoto: UiPhoto) {
        viewModelScope.launch {
            navigationEventEmitter.navigateTo(AppScreen.ViewPhoto(uiPhoto.id))
        }
    }

    override fun addToFavouriteClicked(uiPhoto: UiPhoto) {
        viewModelScope.launch(ioDispatcher) {
            Log.i(FavouritePhotosViewModel::class.java.name, "changePhotoFavouriteState: $uiPhoto")
            favouriteUseCase.removeFromFavourite(uiPhoto.id)
        }
    }
}