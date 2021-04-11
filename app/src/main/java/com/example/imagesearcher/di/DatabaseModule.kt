package com.example.imagesearcher.di

import android.content.Context
import androidx.room.Room
import com.example.imagesearcher.domain.dao.PhotoDao
import com.example.imagesearcher.domain.database.ImageSearcherDatabase
import com.example.imagesearcher.domain.net.UnsplashApi
import com.example.imagesearcher.domain.repo.PhotoRepo
import com.example.imagesearcher.domain.repo.PhotoRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ImageSearcherDatabase {
        return Room.databaseBuilder(
            context,
            ImageSearcherDatabase::class.java,
            ImageSearcherDatabase::class.java.name
        ).build()
    }

    @Provides
    fun providePhotoDao(appDatabase: ImageSearcherDatabase): PhotoDao = appDatabase.getPhotoDao()

    @Provides
    fun providePhotoRepo(unsplashApi: UnsplashApi, photoDao: PhotoDao): PhotoRepo =
        PhotoRepoImpl(unsplashApi, photoDao)
}