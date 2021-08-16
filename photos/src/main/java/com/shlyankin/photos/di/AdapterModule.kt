package com.shlyankin.photos.di

import com.shlyankin.photos.mapper.PhotoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class AdapterModule {

    @Singleton
    @Provides
    fun providePhotoAdapter() = PhotoMapper()
}