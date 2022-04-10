package com.shlyankin.navigation.events

import com.shlyankin.navigation.AppScreen

interface NavigationEventEmitter {
    fun navigateTo(appScreen: AppScreen)
}