package com.shlyankin.myapplication.di

import com.shlyankin.myapplication.datasource.PhotosDataSource
import com.shlyankin.myapplication.repo.photo.PhotoRepo
import com.shlyankin.photos.di.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataSourceModule {

    @Singleton
    @Provides
    fun providePhotosDataSource(repo: PhotoRepo, @IoDispatcher ioDispatcher: CoroutineDispatcher) =
        PhotosDataSource(repo, ioDispatcher)

}