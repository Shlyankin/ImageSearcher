package com.example.imagesearcher.domain.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imagesearcher.BuildConfig
import com.example.imagesearcher.domain.dao.PhotoDao
import com.example.imagesearcher.domain.model.Photo

@Database(
    entities = [
        Photo::class
    ],
    version = BuildConfig.VERSION_CODE
)
abstract class ImageSearcherDatabase : RoomDatabase() {
    abstract fun getPhotoDao(): PhotoDao
}