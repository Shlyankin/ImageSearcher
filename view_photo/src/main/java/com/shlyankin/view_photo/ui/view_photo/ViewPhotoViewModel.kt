package com.shlyankin.view_photo.ui.view_photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shlyankin.photos.di.DefaultDispatcher
import com.shlyankin.photos.di.IoDispatcher
import com.shlyankin.view_photo.model.UiPhoto
import com.shlyankin.view_photo.ui.usecase.ViewPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@HiltViewModel
internal class ViewPhotoViewModel(
    private val viewPhotoUseCase: ViewPhotoUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    @DefaultDispatcher private val defaultDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _photo = MutableStateFlow<UiPhoto?>(null)
    val photo = _photo.filterNotNull()

    private var photoJob: Job? = null

    fun onArgumentsReceived(photoId: String) {
        photoJob?.cancel()
        photoJob = viewModelScope.launch(ioDispatcher) {
            viewPhotoUseCase.getPhoto(photoId).collect {
                _photo.emit(it)
            }
        }
    }

    fun addToFavouriteClicked() {
        viewModelScope.launch(ioDispatcher) {
            _photo.value?.let {
                if (it.isFavourite) {
                    viewPhotoUseCase.removeFromFavourite(it.id)
                } else {
                    viewPhotoUseCase.addToFavourite(it)
                }
            }
        }
    }

}