package com.shlyankin.imagesearcher.di

import android.content.Context
import androidx.room.Room
import com.shlyankin.data.api.FavouriteRepo
import com.shlyankin.data.api.PhotoRepo
import com.shlyankin.data.impl.datasource.PhotosDataSource
import com.shlyankin.data.impl.repo.favourite.FavouriteRepoImpl
import com.shlyankin.data.impl.repo.photo.PhotoRepoImpl
import com.shlyankin.db.ImageSearcherDatabase
import com.shlyankin.db.dao.FavouritePhotoDao
import com.shlyankin.net.file.FileManager
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
    fun provideFavouritePhotoDao(
        appDatabase: ImageSearcherDatabase,
    ): FavouritePhotoDao = appDatabase.getFavouritePhotoDao()

    @Singleton
    @Provides
    fun providePhotoRepo(
        dataSource: PhotosDataSource,
        favouritePhotoDao: FavouritePhotoDao,
    ): PhotoRepo = PhotoRepoImpl(dataSource, favouritePhotoDao)

    @Singleton
    @Provides
    fun provideFavouriteRepo(
        favouritePhotoDao: FavouritePhotoDao,
        fileManager: FileManager,
    ): FavouriteRepo = FavouriteRepoImpl(favouritePhotoDao, fileManager)
}