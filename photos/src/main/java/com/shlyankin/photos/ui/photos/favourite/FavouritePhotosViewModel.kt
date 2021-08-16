package com.shlyankin.photos.ui.photos.favourite

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.shlyankin.photos.di.IoDispatcher
import com.shlyankin.photos.model.UiPhoto
import com.shlyankin.photos.ui.usecase.favourite.FavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FavouritePhotosViewModel @Inject constructor(
    private val favouriteUseCase: FavouriteUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    val favouritePhotos = favouriteUseCase.favouritePhotos
        .map { PagingData.from(it) }.flowOn(ioDispatcher)

    fun addToFavouriteClicked(uiPhoto: UiPhoto) {
        viewModelScope.launch(ioDispatcher) {
            Log.i(FavouritePhotosViewModel::class.java.name, "changePhotoFavouriteState: $uiPhoto")
            favouriteUseCase.removeFromFavourite(uiPhoto.id)
        }
    }
}