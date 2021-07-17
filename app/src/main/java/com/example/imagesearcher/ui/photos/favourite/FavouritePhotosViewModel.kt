package com.example.imagesearcher.ui.photos.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearcher.domain.adapter.PhotoMapper
import com.example.imagesearcher.domain.model.ui.UiPhoto
import com.example.imagesearcher.domain.usecase.FavouriteUseCase
import com.example.imagesearcher.utils.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouritePhotosViewModel @Inject constructor(
    private val favouriteUseCase: FavouriteUseCase,
    private val photosMapper: PhotoMapper,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {


    private val _favouritePhotos = MutableLiveData<List<UiPhoto>>()
    val favouritePhotos = _favouritePhotos.liveData()

    init {
        viewModelScope.launch {
            favouriteUseCase.favouritePhotos.collect {
                _favouritePhotos.postValue(it)
            }
        }
    }

    fun addToFavouriteClicked(uiPhoto: UiPhoto) {
        viewModelScope.launch {
            favouriteUseCase.changePhotoFavouriteState(uiPhoto)
        }
    }
}