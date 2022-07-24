package com.shlyankin.imagesearcher.di

import com.shlyankin.data.impl.datasource.PhotosDataSource
import com.shlyankin.data.impl.datasource.PhotosDataSourceImpl
import com.shlyankin.net.UnsplashApi
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
    fun providePhotosDataSource(
        unsplashApi: UnsplashApi,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): PhotosDataSource = PhotosDataSourceImpl(unsplashApi, ioDispatcher)

}