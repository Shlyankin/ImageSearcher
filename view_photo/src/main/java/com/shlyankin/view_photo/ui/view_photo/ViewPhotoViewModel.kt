package com.shlyankin.view_photo.ui.view_photo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shlyankin.util.utils.into
import com.shlyankin.view_photo.model.UiPhoto
import com.shlyankin.view_photo.ui.usecase.ViewPhotoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
internal class ViewPhotoViewModel @Inject constructor(
    private val viewPhotoUseCase: ViewPhotoUseCase,
) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()

    private val _photo = MutableLiveData<UiPhoto>()
    val photo: LiveData<UiPhoto> = _photo

    init {
        viewPhotoUseCase.photo
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext {
                _photo.value = it
            }.subscribe() into compositeDisposable
    }

    fun onArgumentsReceived(photoId: String) {
        viewPhotoUseCase.getPhoto(photoId)
    }

    fun addToFavouriteClicked() {
        _photo.value?.let {
            if (it.isFavourite) {
                viewPhotoUseCase.removeFromFavourite()
            } else {
                viewPhotoUseCase.addToFavourite()
            }
        }
    }
}