package com.shlyankin.imagesearcher.ui.photos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shlyankin.imagesearcher.di.IoDispatcher
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto
import com.shlyankin.imagesearcher.domain.usecase.favourite.FavouriteUseCase
import com.shlyankin.imagesearcher.domain.usecase.photo.PhotosUseCase
import com.shlyankin.imagesearcher.utils.combineNotNull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photosUseCase: PhotosUseCase,
    private val favouriteUseCase: FavouriteUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _favouritePhotos = MutableLiveData<List<UiPhoto>>()
    private val _photos = MutableLiveData<List<UiPhoto>>()

    val photos = _favouritePhotos.combineNotNull(_photos) { favourite, all ->
        return@combineNotNull mutableListOf<UiPhoto>().apply {
            all.onEach { f ->
                add(f.copy(isFavourite = favourite.find { it.id == f.id } != null))
            }
        }
    }

    fun addToFavouriteClicked(uiPhoto: UiPhoto) {
        viewModelScope.launch(ioDispatcher) {
            favouriteUseCase.changePhotoFavouriteState(uiPhoto)
        }
    }

    init {
        viewModelScope.launch(ioDispatcher) {
            photosUseCase.updatePhotos(1)
        }
        viewModelScope.launch(ioDispatcher) {
            photosUseCase.photos.collect {
                _photos.postValue(it)
            }
        }
        viewModelScope.launch(ioDispatcher) {
            favouriteUseCase.favouritePhotos.collect {
                _favouritePhotos.postValue(it)
            }
        }
    }
}