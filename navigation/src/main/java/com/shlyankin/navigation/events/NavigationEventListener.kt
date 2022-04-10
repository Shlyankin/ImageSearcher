package com.shlyankin.navigation.events

import com.shlyankin.navigation.AppScreen
import io.reactivex.rxjava3.subjects.Subject

interface NavigationEventListener {
    val navigationEvent: Subject<AppScreen>
}