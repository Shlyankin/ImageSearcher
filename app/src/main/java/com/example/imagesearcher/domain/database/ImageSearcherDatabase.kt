package com.example.imagesearcher.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.imagesearcher.BuildConfig
import com.example.imagesearcher.domain.converters.DateConverter
import com.example.imagesearcher.domain.dao.FavouritePhotoDao
import com.example.imagesearcher.domain.dao.PhotoDao
import com.example.imagesearcher.domain.model.FavouritePhoto
import com.example.imagesearcher.domain.model.PhotoEntity

@Database(
    entities = [
        PhotoEntity::class,
        FavouritePhoto::class,
    ],
    version = BuildConfig.VERSION_CODE
)
@TypeConverters(DateConverter::class)
abstract class ImageSearcherDatabase : RoomDatabase() {
    abstract fun getPhotoDao(): PhotoDao
    abstract fun getFavouritePhotoDao(): FavouritePhotoDao
}