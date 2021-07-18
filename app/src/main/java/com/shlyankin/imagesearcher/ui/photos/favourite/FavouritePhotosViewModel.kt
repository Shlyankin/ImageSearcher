package com.shlyankin.imagesearcher.ui.photos.favourite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shlyankin.imagesearcher.di.IoDispatcher
import com.shlyankin.imagesearcher.domain.model.ui.UiPhoto
import com.shlyankin.imagesearcher.domain.usecase.FavouriteUseCase
import com.shlyankin.imagesearcher.utils.liveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.logging.Logger
import javax.inject.Inject

@HiltViewModel
class FavouritePhotosViewModel @Inject constructor(
    private val favouriteUseCase: FavouriteUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val logger: Logger = Logger.getLogger(FavouritePhotosFragment::class.java.name)

    private val _favouritePhotos = MutableLiveData<List<UiPhoto>>()
    val favouritePhotos = _favouritePhotos.liveData()

    init {
        viewModelScope.launch(ioDispatcher) {
            favouriteUseCase.favouritePhotos.collect {
                logger.info("collect: $it")
                _favouritePhotos.postValue(it)
            }
        }
    }

    fun addToFavouriteClicked(uiPhoto: UiPhoto) {
        viewModelScope.launch(ioDispatcher) {
            logger.info("changePhotoFavouriteState: $uiPhoto")
            favouriteUseCase.changePhotoFavouriteState(uiPhoto)
        }
    }

    override fun onCleared() {
        logger.info("onCleared")
        super.onCleared()
    }
}