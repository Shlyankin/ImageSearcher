package com.shlyankin.view_photo.ui.view_photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shlyankin.photos.di.IoDispatcher
import com.shlyankin.view_photo.ui.usecase.ViewPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ViewPhotoViewModel @Inject constructor(
    private val viewPhotoUseCase: ViewPhotoUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _photo = viewPhotoUseCase.photo.flowOn(ioDispatcher)
    val photo = _photo.filterNotNull()

    fun onArgumentsReceived(photoId: String) {
        viewModelScope.launch(ioDispatcher) {
            viewPhotoUseCase.getPhoto(photoId)
        }
    }

    fun addToFavouriteClicked() {
        viewModelScope.launch(ioDispatcher) {
            _photo.firstOrNull()?.let {
                if (it.isFavourite) {
                    viewPhotoUseCase.removeFromFavourite()
                } else {
                    viewPhotoUseCase.addToFavourite()
                }
            }
        }
    }

}