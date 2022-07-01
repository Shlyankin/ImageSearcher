package com.shlyankin.myapplication.di

import android.content.Context
import com.shlyankin.myapplication.net.FileApi
import com.shlyankin.myapplication.net.file.FileManager
import com.shlyankin.myapplication.net.file.FileManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class ManagerModule {

    @Singleton
    @Provides
    fun provideFileManager(
        fileApi: FileApi,
        @ApplicationContext context: Context,
    ): FileManager = FileManagerImpl(fileApi, context)
}