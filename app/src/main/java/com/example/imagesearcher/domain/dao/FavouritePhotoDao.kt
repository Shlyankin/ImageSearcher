package com.example.imagesearcher.domain.dao

import androidx.room.Dao
import com.example.imagesearcher.domain.model.FavouritePhoto

@Dao
interface FavouritePhotoDao: BaseDao<FavouritePhoto> {
}