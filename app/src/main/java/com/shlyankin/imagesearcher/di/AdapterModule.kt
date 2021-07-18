package com.shlyankin.imagesearcher.di

import com.shlyankin.imagesearcher.domain.adapter.PhotoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Singleton
    @Provides
    fun providePhotoAdapter() = PhotoMapper()
}