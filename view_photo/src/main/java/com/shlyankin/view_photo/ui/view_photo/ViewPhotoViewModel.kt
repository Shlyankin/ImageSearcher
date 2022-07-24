package com.shlyankin.view_photo.ui.view_photo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shlyankin.domain.api.ViewPhotoUseCase
import com.shlyankin.photos.di.IoDispatcher
import com.shlyankin.view_photo.mapper.toDomain
import com.shlyankin.view_photo.mapper.toPres
import com.shlyankin.view_photo.model.PresPhoto
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ViewPhotoViewModel @Inject constructor(
    private val viewPhotoUseCase: ViewPhotoUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _photo = MutableStateFlow<PresPhoto?>(null)
    val photo = _photo.filterNotNull()

    fun onArgumentsReceived(photoId: String) {
        viewModelScope.launch(ioDispatcher) {
            viewPhotoUseCase.getPhoto(photoId).collect { photo ->
                _photo.emit(photo?.toPres())
            }
        }
    }

    fun addToFavouriteClicked() {
        viewModelScope.launch(ioDispatcher) {
            _photo.firstOrNull()?.let { photo ->
                if (photo.isFavourite) {
                    viewPhotoUseCase.removeFromFavourite(photo.toDomain())
                } else {
                    viewPhotoUseCase.addToFavourite(photo.toDomain())
                }
            }
        }
    }

}