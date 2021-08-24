package com.shlyankin.navigation.events

import com.shlyankin.navigation.AppScreen
import kotlinx.coroutines.flow.MutableSharedFlow

internal class NavigationEventHolder : NavigationEventEmitter, NavigationEventListener {

    override suspend fun navigateTo(appScreen: AppScreen) {
        navigationEvent.emit(appScreen)
    }

    override val navigationEvent = MutableSharedFlow<AppScreen>()
}