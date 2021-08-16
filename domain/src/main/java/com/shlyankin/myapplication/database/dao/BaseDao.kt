package com.shlyankin.myapplication.database.dao

import androidx.room.*

@Dao
internal interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReplace(t: T)

    @Delete
    suspend fun delete(t: T)

    @Update
    suspend fun update(t: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReplace(t: List<T>)
}