package com.shlyankin.imagesearcher.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.shlyankin.imagesearcher.domain.converters.DateConverter
import com.shlyankin.imagesearcher.domain.dao.FavouritePhotoDao
import com.shlyankin.imagesearcher.domain.model.FavouritePhoto

@Database(
    entities = [
        FavouritePhoto::class,
    ],
    exportSchema = true,
    version = 10
)
@TypeConverters(DateConverter::class)
abstract class ImageSearcherDatabase : RoomDatabase() {
    abstract fun getFavouritePhotoDao(): FavouritePhotoDao
}