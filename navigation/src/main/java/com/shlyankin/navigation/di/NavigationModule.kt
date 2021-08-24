package com.shlyankin.navigation.di

import com.shlyankin.navigation.events.NavigationEventEmitter
import com.shlyankin.navigation.events.NavigationEventHolder
import com.shlyankin.navigation.events.NavigationEventListener
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class NavigationModule {

    @Singleton
    @Provides
    fun provideNavigationHolder() = NavigationEventHolder()

    @Singleton
    @Provides
    fun provideNavigationEmitter(
        navigationEventHolder: NavigationEventHolder
    ): NavigationEventEmitter = navigationEventHolder

    @Singleton
    @Provides
    fun provideNavigationListener(
        navigationEventHolder: NavigationEventHolder
    ): NavigationEventListener = navigationEventHolder

}