package com.example.imagesearcher.domain.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.imagesearcher.domain.model.PhotoEntity

@Dao
interface PhotoDao : BaseDao<PhotoEntity> {

    @Query("delete from PhotoEntity")
    fun deleteAll()

}