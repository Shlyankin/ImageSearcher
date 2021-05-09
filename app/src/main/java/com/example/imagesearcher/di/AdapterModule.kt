package com.example.imagesearcher.di

import com.example.imagesearcher.domain.adapter.PhotoAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AdapterModule {

    @Provides
    fun providePhotoAdapter() = PhotoAdapter()
}