package com.shlyankin.view_photo.di

import com.shlyankin.view_photo.mapper.PhotoMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class AdapterModule {

    @Singleton
    @Provides
    fun providePhotoAdapter(locale: Locale) = PhotoMapper(locale)
}