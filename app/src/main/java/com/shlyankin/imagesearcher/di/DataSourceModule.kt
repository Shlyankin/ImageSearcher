package com.shlyankin.imagesearcher.di

import com.shlyankin.imagesearcher.domain.datasource.photo.PhotosDataSource
import com.shlyankin.imagesearcher.domain.repo.photo.PhotoRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataSourceModule {

    @Singleton
    @Provides
    fun providePhotosDataSource(repo: PhotoRepo, @IoDispatcher ioDispatcher: CoroutineDispatcher) =
        PhotosDataSource(repo, ioDispatcher)

}