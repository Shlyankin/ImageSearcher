package com.shlyankin.myapplication.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.shlyankin.myapplication.database.model.FavouritePhoto
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FavouritePhotoDao : BaseDao<FavouritePhoto> {

    @Query("SELECT * FROM FavouritePhoto")
    fun getAll(): Flow<List<FavouritePhoto>>

    @Query("DELETE FROM FavouritePhoto where id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM FavouritePhoto")
    suspend fun getAllSuspend(): List<FavouritePhoto>

    @Query("SELECT * FROM FavouritePhoto WHERE id=:id")
    suspend fun get(id: String): FavouritePhoto?

}