package com.shlyankin.myapplication.database.dao

import androidx.room.*
import io.reactivex.rxjava3.core.Completable

@Dao
internal interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplace(t: T): Completable

    @Delete
    fun delete(t: T): Completable

    @Update
    fun update(t: T): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplace(t: List<T>): Completable
}