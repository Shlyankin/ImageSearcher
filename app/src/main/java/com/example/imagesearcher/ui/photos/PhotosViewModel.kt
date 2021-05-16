package com.example.imagesearcher.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearcher.domain.adapter.PhotoAdapter
import com.example.imagesearcher.domain.model.FavouritePhoto
import com.example.imagesearcher.domain.model.ui.UiPhoto
import com.example.imagesearcher.domain.usecase.FavouriteUseCase
import com.example.imagesearcher.domain.usecase.PhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photosUseCase: PhotoUseCase,
    private val favouriteUseCase: FavouriteUseCase,
    private val photosAdapter: PhotoAdapter
) : ViewModel() {

    private val _favouritePhotos = MutableStateFlow<List<FavouritePhoto>>(emptyList())
    val photos = photosUseCase.photos.combine(_favouritePhotos) { all, favourite ->
        photosAdapter.convertFromPhotoToUiPhoto(all, favourite)
    }

    fun addToFavouriteClicked(uiPhoto: UiPhoto) {
        viewModelScope.launch {
            photosUseCase.photos.value.find { it.id == uiPhoto.id }?.let {
                if (!uiPhoto.isFavourite) {
                    favouriteUseCase.addToFavourite(it)
                } else {
                    favouriteUseCase.deleteFromFavourites(it)
                }
            }
        }
    }

    init {
        viewModelScope.launch {
            photosUseCase.updatePhotos(1)
        }
        viewModelScope.launch {
            favouriteUseCase.favouritePhotos.collect {
                _favouritePhotos.emit(it)
            }
        }
    }

}