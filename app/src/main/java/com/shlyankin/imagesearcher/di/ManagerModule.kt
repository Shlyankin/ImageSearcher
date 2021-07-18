package com.shlyankin.imagesearcher.di

import android.content.Context
import com.shlyankin.imagesearcher.domain.net.FileApi
import com.shlyankin.imagesearcher.manager.FileManager
import com.shlyankin.imagesearcher.manager.FileManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ManagerModule {

    @Singleton
    @Provides
    fun provideFileManager(fileApi: FileApi, @ApplicationContext context: Context): FileManager =
        FileManagerImpl(fileApi, context)
}