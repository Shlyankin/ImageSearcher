package com.shlyankin.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.shlyankin.myapplication.database.model.FavouritePhoto
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

@Dao
internal interface FavouritePhotoDao : BaseDao<FavouritePhoto> {

    @Query("SELECT * FROM FavouritePhoto")
    fun getAll(): Observable<List<FavouritePhoto>>

    @Query("DELETE FROM FavouritePhoto where id = :id")
    fun deleteById(id: String): Completable

    @Query("SELECT * FROM FavouritePhoto")
    fun getAllSingle(): Single<List<FavouritePhoto>>

    @Query("SELECT * FROM FavouritePhoto WHERE id=:id")
    fun get(id: String): Single<FavouritePhoto>

}