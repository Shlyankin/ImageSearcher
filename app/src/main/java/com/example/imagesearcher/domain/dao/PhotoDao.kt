package com.example.imagesearcher.domain.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.imagesearcher.domain.model.Photo

@Dao
interface PhotoDao : BaseDao<Photo> {

    @Query("delete from Photo")
    fun deleteAll()

}