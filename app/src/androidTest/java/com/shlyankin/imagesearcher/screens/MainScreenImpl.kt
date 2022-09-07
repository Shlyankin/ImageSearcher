package com.shlyankin.imagesearcher.screens

import com.kaspersky.components.kautomator.screen.UiScreen
import com.shlyankin.imagesearcher.screenName
import com.shlyankin.imagesearcher.ui.MainActivity

class MainScreenImpl : UiScreen<MainScreenImpl>() {
    override val packageName: String
        get() = MainActivity::class.screenName()
}

fun MainScreen(action: MainScreenImpl.() -> Unit) = MainScreenImpl().invoke(action)
