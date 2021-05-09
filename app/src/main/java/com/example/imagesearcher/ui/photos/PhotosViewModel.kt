package com.example.imagesearcher.ui.photos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imagesearcher.domain.adapter.PhotoAdapter
import com.example.imagesearcher.domain.usecase.PhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val photosUseCase: PhotoUseCase,
    private val photosAdapter: PhotoAdapter
) : ViewModel() {

    val photos = photosUseCase.photos.map { photosAdapter.convertFromPhotoToUiPhoto(it) }

    init {
        viewModelScope.launch {
            photosUseCase.updatePhotos(1)
        }
    }

}