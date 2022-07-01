package com.shlyankin.myapplication.di

import android.content.Context
import androidx.room.Room
import com.shlyankin.myapplication.database.ImageSearcherDatabase
import com.shlyankin.myapplication.database.dao.FavouritePhotoDao
import com.shlyankin.myapplication.net.UnsplashApi
import com.shlyankin.myapplication.net.file.FileManager
import com.shlyankin.myapplication.repo.favourite.FavouriteRepo
import com.shlyankin.myapplication.repo.favourite.FavouriteRepoImpl
import com.shlyankin.myapplication.repo.photo.PhotoRepo
import com.shlyankin.myapplication.repo.photo.PhotoRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ImageSearcherDatabase {
        return Room.databaseBuilder(
            context,
            ImageSearcherDatabase::class.java,
            ImageSearcherDatabase::class.java.name
        ).build()
    }

    @Singleton
    @Provides
    fun provideFavouritePhotoDao(appDatabase: ImageSearcherDatabase): FavouritePhotoDao =
        appDatabase.getFavouritePhotoDao()

    @Singleton
    @Provides
    fun providePhotoRepo(
        unsplashApi: UnsplashApi,
    ): PhotoRepo = PhotoRepoImpl(unsplashApi)

    @Singleton
    @Provides
    fun provideFavouriteRepo(
        favouritePhotoDao: FavouritePhotoDao,
        fileManager: FileManager,
    ): FavouriteRepo = FavouriteRepoImpl(favouritePhotoDao, fileManager)
}