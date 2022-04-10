package com.shlyankin.view_photo.ui.usecase

import com.shlyankin.myapplication.net.mapper.toEntity
import com.shlyankin.myapplication.net.model.PhotoResponse
import com.shlyankin.myapplication.repo.favourite.FavouriteRepo
import com.shlyankin.myapplication.repo.photo.PhotoRepo
import com.shlyankin.view_photo.mapper.PhotoMapper
import com.shlyankin.view_photo.model.UiPhoto
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

internal class ViewPhotoUseCaseImpl(
    private val photoRepo: PhotoRepo,
    private val favouriteRepo: FavouriteRepo,
    private val photoMapper: PhotoMapper
) : ViewPhotoUseCase {

    private val favouritePhotos = favouriteRepo.getAll()

    private val _photoResp = BehaviorSubject.create<PhotoResponse>()

    override val photo: Observable<UiPhoto> = Observable.combineLatest(
        _photoResp, favouritePhotos
    ) { photoResponse, favouritePhotos ->
        photoMapper.convertFromPhotoToUiPhoto(photoResponse, favouritePhotos)
    }

    override fun getPhoto(photoId: String) {
        photoRepo.getPhoto(photoId).subscribeOn(Schedulers.io()).doOnSuccess {
            _photoResp.onNext(it)
        }.subscribe()
    }

    override fun addToFavourite() {
        _photoResp.value?.let {
            favouriteRepo.addToFavourite(it.toEntity())
                .subscribeOn(Schedulers.io())
                .subscribe()
        }
    }

    override fun removeFromFavourite() {
        _photoResp.value?.let {
            favouriteRepo.deleteFromFavourite(it.toEntity())
                .subscribeOn(Schedulers.io())
                .subscribe()
        }
    }
}