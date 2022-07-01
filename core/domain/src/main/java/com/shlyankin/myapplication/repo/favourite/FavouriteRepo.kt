package com.shlyankin.myapplication.repo.favourite

import com.shlyankin.myapplication.database.model.FavouritePhoto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface FavouriteRepo {

    fun getAll(): Observable<List<FavouritePhoto>>

    fun addToFavourite(photo: FavouritePhoto): Completable

    fun deleteFromFavourite(photoId: String)

    fun deleteFromFavourite(photo: FavouritePhoto): Completable

    fun get(id: String): Single<FavouritePhoto>
}