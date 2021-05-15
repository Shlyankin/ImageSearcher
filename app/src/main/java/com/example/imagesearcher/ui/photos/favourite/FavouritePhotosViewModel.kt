package com.example.imagesearcher.ui.photos.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearcher.domain.adapter.PhotoAdapter
import com.example.imagesearcher.domain.model.ui.UiPhoto
import com.example.imagesearcher.domain.usecase.FavouriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritePhotosViewModel @Inject constructor(
    private val favouriteUseCase: FavouriteUseCase,
    private val photosAdapter: PhotoAdapter,
) : ViewModel() {

    private val _favouritePhotos = favouriteUseCase.favouritePhotos.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        emptyList()
    )
    val favouritePhotos = _favouritePhotos.map { photosAdapter.convertFromPhotoToUiPhoto(it) }


    fun addToFavouriteClicked(position: Int, uiPhoto: UiPhoto) {
        viewModelScope.launch {
            _favouritePhotos.value.getOrNull(position)?.let {
                if (!uiPhoto.isFavourite) {
                    favouriteUseCase.addToFavourite(it)
                } else {
                    favouriteUseCase.deleteFromFavourites(it)
                }
            }
        }
    }
}