package com.shlyankin.imagesearcher.di

import android.content.Context
import com.shlyankin.net.file.FileManager
import com.shlyankin.net.file.FileManagerImpl
import com.shlyankin.photos.di.IoDispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class ManagerModule {

    @Singleton
    @Provides
    fun provideFileManager(
        fileApi: com.shlyankin.net.FileApi,
        @ApplicationContext context: Context,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): FileManager = FileManagerImpl(fileApi, context, ioDispatcher)
}