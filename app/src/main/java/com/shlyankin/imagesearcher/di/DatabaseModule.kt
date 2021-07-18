package com.shlyankin.imagesearcher.di

import android.content.Context
import androidx.room.Room
import com.shlyankin.imagesearcher.domain.adapter.PhotoMapper
import com.shlyankin.imagesearcher.domain.dao.FavouritePhotoDao
import com.shlyankin.imagesearcher.domain.dao.PhotoDao
import com.shlyankin.imagesearcher.domain.database.ImageSearcherDatabase
import com.shlyankin.imagesearcher.domain.net.UnsplashApi
import com.shlyankin.imagesearcher.domain.repo.FavouriteRepo
import com.shlyankin.imagesearcher.domain.repo.FavouriteRepoImpl
import com.shlyankin.imagesearcher.domain.repo.PhotoRepo
import com.shlyankin.imagesearcher.domain.repo.PhotoRepoImpl
import com.shlyankin.imagesearcher.manager.FileManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

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
    fun providePhotoDao(appDatabase: ImageSearcherDatabase): PhotoDao = appDatabase.getPhotoDao()

    @Singleton
    @Provides
    fun provideFavouritePhotoDao(appDatabase: ImageSearcherDatabase): FavouritePhotoDao =
        appDatabase.getFavouritePhotoDao()

    @Singleton
    @Provides
    fun providePhotoRepo(unsplashApi: UnsplashApi, photoDao: PhotoDao): PhotoRepo =
        PhotoRepoImpl(unsplashApi, photoDao)

    @Singleton
    @Provides
    fun provideFavouriteRepo(
        favouritePhotoDao: FavouritePhotoDao,
        photoMapper: PhotoMapper,
        fileManager: FileManager
    ): FavouriteRepo = FavouriteRepoImpl(favouritePhotoDao, photoMapper, fileManager)
}