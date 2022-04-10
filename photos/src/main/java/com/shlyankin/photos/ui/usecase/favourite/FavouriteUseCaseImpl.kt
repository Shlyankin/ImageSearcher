package com.shlyankin.photos.ui.usecase.favourite

import com.shlyankin.myapplication.repo.favourite.FavouriteRepo
import com.shlyankin.photos.mapper.PhotoMapper
import com.shlyankin.photos.model.UiPhoto
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

internal class FavouriteUseCaseImpl(
    private val favouriteRepo: FavouriteRepo,
    private val photosMapper: PhotoMapper
) : FavouriteUseCase {

    private val rawFavouritePhotos = favouriteRepo.getAll()
    override val favouritePhotos: Observable<List<UiPhoto>> = rawFavouritePhotos.map {
        photosMapper.convertFromFavouritePhotoToUiPhoto(it).reversed()
    }

    override fun addToFavourite(photo: UiPhoto) {
        favouriteRepo.addToFavourite(photosMapper.convertFromUiPhoto(photo))
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

    override fun removeFromFavourite(id: String) {
        favouriteRepo.get(id).flatMapCompletable {
            favouriteRepo.deleteFromFavourite(it)
        }.subscribeOn(Schedulers.io())
            .subscribe()
    }
}