package com.shlyankin.photos.ui.photos.favourite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.shlyankin.domain.api.FavouriteUseCase
import com.shlyankin.navigation.AppScreen
import com.shlyankin.navigation.events.NavigationEventEmitter
import com.shlyankin.photos.di.IoDispatcher
import com.shlyankin.photos.mapper.toPres
import com.shlyankin.photos.model.PresPhoto
import com.shlyankin.photos.ui.photos.PhotosViewModel
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
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel(), PhotosViewModel {

    override val photos: Flow<PagingData<PresPhoto>> = favouriteUseCase.favouritePhotos
        .map { domainPhotos ->
            PagingData.from(domainPhotos.toPres())
        }.flowOn(ioDispatcher)

    override fun onPhotoClicked(uiPhoto: PresPhoto) {
        viewModelScope.launch {
            navigationEventEmitter.navigateTo(AppScreen.ViewPhoto(uiPhoto.id))
        }
    }

    override fun addToFavouriteClicked(uiPhoto: PresPhoto) {
        viewModelScope.launch(ioDispatcher) {
            Log.i(FavouritePhotosViewModel::class.java.name, "changePhotoFavouriteState: $uiPhoto")
            favouriteUseCase.removeFromFavourite(uiPhoto.id)
        }
    }
}