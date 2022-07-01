package com.shlyankin.imagesearcher.di

import android.content.Context
import androidx.room.Room
import com.shlyankin.data.impl.FavouriteRepoImpl
import com.shlyankin.data.impl.PhotoRepoImpl
import com.shlyankin.db.database.ImageSearcherDatabase
import com.shlyankin.db.database.dao.FavouritePhotoDao
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
        unsplashApi: com.shlyankin.net.UnsplashApi,
    ): com.shlyankin.data.api.PhotoRepo = com.shlyankin.data.impl.PhotoRepoImpl(unsplashApi)

    @Singleton
    @Provides
    fun provideFavouriteRepo(
        favouritePhotoDao: FavouritePhotoDao,
        fileManager: com.shlyankin.net.file.FileManager,
    ): com.shlyankin.data.api.FavouriteRepo =
        com.shlyankin.data.impl.FavouriteRepoImpl(favouritePhotoDao, fileManager)
}