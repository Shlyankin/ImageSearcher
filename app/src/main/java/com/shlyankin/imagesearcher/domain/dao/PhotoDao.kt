package com.shlyankin.imagesearcher.domain.dao

import androidx.room.Dao
import androidx.room.Query
import com.shlyankin.imagesearcher.domain.model.PhotoEntity

@Dao
interface PhotoDao : BaseDao<PhotoEntity> {

    @Query("delete from PhotoEntity")
    fun deleteAll()

    @Query("select * from PhotoEntity where id = :photoId")
    fun getPhoto(photoId: String): PhotoEntity?

}