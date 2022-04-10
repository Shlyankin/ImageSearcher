package com.shlyankin.myapplication.di

import com.shlyankin.myapplication.datasource.PhotosDataSource
import com.shlyankin.myapplication.repo.photo.PhotoRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class DataSourceModule {

    @Singleton
    @Provides
    fun providePhotosDataSource(repo: PhotoRepo) = PhotosDataSource(repo)

}