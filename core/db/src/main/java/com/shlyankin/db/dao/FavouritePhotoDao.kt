package com.shlyankin.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.shlyankin.db.model.FavouritePhotoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritePhotoDao : BaseDao<FavouritePhotoEntity> {

    @Query("SELECT * FROM FavouritePhotoEntity ORDER BY addedInFavouriteAt DESC")
    fun getAll(): Flow<List<FavouritePhotoEntity>>

    @Query("DELETE FROM FavouritePhotoEntity where id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM FavouritePhotoEntity")
    suspend fun getAllSuspend(): List<FavouritePhotoEntity>

    @Query("SELECT * FROM FavouritePhotoEntity WHERE id=:id")
    suspend fun get(id: String): FavouritePhotoEntity?

}