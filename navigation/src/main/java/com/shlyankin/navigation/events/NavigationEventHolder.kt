package com.shlyankin.navigation.events

import com.shlyankin.navigation.AppScreen
import io.reactivex.rxjava3.subjects.PublishSubject

internal class NavigationEventHolder : NavigationEventEmitter, NavigationEventListener {

    override fun navigateTo(appScreen: AppScreen) {
        navigationEvent.onNext(appScreen)
    }

    override val navigationEvent: PublishSubject<AppScreen> = PublishSubject.create()
}