package com.example.imagesearcher.ui.photos.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearcher.domain.adapter.PhotoAdapter
import com.example.imagesearcher.domain.model.FavouritePhoto
import com.example.imagesearcher.domain.model.ui.UiPhoto
import com.example.imagesearcher.domain.usecase.FavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritePhotosViewModel @Inject constructor(
    private val favouriteUseCase: FavouriteUseCase,
    private val photosAdapter: PhotoAdapter,
) : ViewModel() {

    private val _favouritePhotos = MutableStateFlow<List<FavouritePhoto>>(emptyList())
    val favouritePhotos =
        _favouritePhotos.map { photosAdapter.convertFromFavouritePhotoToUiPhoto(it) }

    init {
        viewModelScope.launch {
            favouriteUseCase.favouritePhotos.collect {
                _favouritePhotos.emit(it)
            }
        }
    }

    fun addToFavouriteClicked(uiPhoto: UiPhoto) {
        viewModelScope.launch {
            favouriteUseCase.favouritePhotos.firstOrNull()?.find { it.id == uiPhoto.id }?.let {
                if (!uiPhoto.isFavourite) {
                    favouriteUseCase.addToFavourite(it)
                } else {
                    favouriteUseCase.deleteFromFavourites(it)
                }
            }
        }
    }
}