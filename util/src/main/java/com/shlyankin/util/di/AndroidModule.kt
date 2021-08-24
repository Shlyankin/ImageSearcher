package com.shlyankin.util.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
internal class DispatchersModule {

    @Provides
    fun provideLocale(
        @ApplicationContext context: Context
    ): Locale = context.resources.configuration.locales.get(0)

}
