package com.shlyankin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shlyankin.db.converter.DateConverter
import com.shlyankin.db.dao.FavouritePhotoDao
import com.shlyankin.db.model.FavouritePhotoEntity

@Database(
    entities = [
        FavouritePhotoEntity::class,
    ],
    exportSchema = false,
    version = 10
)
@TypeConverters(DateConverter::class)
abstract class ImageSearcherDatabase : RoomDatabase() {
    abstract fun getFavouritePhotoDao(): FavouritePhotoDao
}