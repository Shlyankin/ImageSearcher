package com.example.imagesearcher.di

import android.content.Context
import com.example.imagesearcher.domain.net.FileApi
import com.example.imagesearcher.manager.FileManager
import com.example.imagesearcher.manager.FileManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ManagerModule {

    @Provides
    fun provideFileManager(fileApi: FileApi, @ApplicationContext context: Context): FileManager =
        FileManagerImpl(fileApi, context)
}