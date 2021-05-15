package com.example.imagesearcher.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearcher.domain.adapter.PhotoAdapter
import com.example.imagesearcher.domain.model.ui.UiPhoto
import com.example.imagesearcher.domain.usecase.FavouriteUseCase
import com.example.imagesearcher.domain.usecase.PhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photosUseCase: PhotoUseCase,
    private val favouriteUseCase: FavouriteUseCase,
    private val photosAdapter: PhotoAdapter
) : ViewModel() {

    private val favouritePhotos = favouriteUseCase.favouritePhotos
    val photos = photosUseCase.photos.combine(favouritePhotos) { all, favourite ->
        photosAdapter.convertFromPhotoToUiPhoto(all, favourite)
    }

    fun addToFavouriteClicked(position: Int, uiPhoto: UiPhoto) {
        viewModelScope.launch {
            photosUseCase.photos.value.getOrNull(position)?.let {
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
    }
}