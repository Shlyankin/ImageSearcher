package com.shlyankin.navigation.events

import com.shlyankin.navigation.AppScreen

interface NavigationEventEmitter {
    suspend fun navigateTo(appScreen: AppScreen)
}