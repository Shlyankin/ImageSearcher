package com.example.imagesearcher.ui.photos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearcher.domain.model.ui.UiPhoto
import com.example.imagesearcher.domain.usecase.FavouriteUseCase
import com.example.imagesearcher.domain.usecase.PhotoUseCase
import com.example.imagesearcher.utils.combineNotNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photosUseCase: PhotoUseCase,
    private val favouriteUseCase: FavouriteUseCase,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _favouritePhotos = MutableLiveData<List<UiPhoto>>()
    private val _photos = MutableLiveData<List<UiPhoto>>()
    val photos = _favouritePhotos.combineNotNull(_photos) { favourite, all ->
        all.onEach { f ->
            f.isFavourite = favourite.find { it.id == f.id } != null
        }
    }

    fun addToFavouriteClicked(uiPhoto: UiPhoto) {
        viewModelScope.launch {
            favouriteUseCase.changePhotoFavouriteState(uiPhoto)
        }
    }

    init {
        viewModelScope.launch {
            photosUseCase.updatePhotos(1)
        }
        viewModelScope.launch(ioDispatcher) {
            favouriteUseCase.favouritePhotos.collect {
                _photos.postValue(it)
            }
        }
    }
}