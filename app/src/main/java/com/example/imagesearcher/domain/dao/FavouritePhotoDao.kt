package com.example.imagesearcher.domain.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.imagesearcher.domain.model.FavouritePhoto
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritePhotoDao: BaseDao<FavouritePhoto> {

    @Query("SELECT * FROM FavouritePhoto")
    fun getAll(): Flow<List<FavouritePhoto>>

    @Query("DELETE FROM FavouritePhoto where id = :id")
    suspend fun deleteById(id: String)

}