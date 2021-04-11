package com.example.imagesearcher.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearcher.domain.usecase.PhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photosUseCase: PhotoUseCase
) : ViewModel() {

    val photos = photosUseCase.photos

    init {
        viewModelScope.launch {
            photosUseCase.updatePhotos(1)
        }
    }

}