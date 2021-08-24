package com.shlyankin.navigation.events

import com.shlyankin.navigation.AppScreen
import kotlinx.coroutines.flow.Flow

interface NavigationEventListener {
    val navigationEvent: Flow<AppScreen>
}