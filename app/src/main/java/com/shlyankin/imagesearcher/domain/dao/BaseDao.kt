package com.shlyankin.imagesearcher.domain.dao

import androidx.room.*

@Dao
interface BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReplace(t: T)

    @Delete
    suspend fun delete(t: T)

    @Update
    suspend fun update(t: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReplace(t: List<T>)
}