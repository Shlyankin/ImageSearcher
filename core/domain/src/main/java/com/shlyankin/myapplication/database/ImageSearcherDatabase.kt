package com.shlyankin.myapplication.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shlyankin.myapplication.database.converter.DateConverter
import com.shlyankin.myapplication.database.dao.FavouritePhotoDao
import com.shlyankin.myapplication.database.model.FavouritePhoto

@Database(
    entities = [
        FavouritePhoto::class,
    ],
    exportSchema = false,
    version = 10
)
@TypeConverters(DateConverter::class)
internal abstract class ImageSearcherDatabase : RoomDatabase() {
    abstract fun getFavouritePhotoDao(): FavouritePhotoDao
}