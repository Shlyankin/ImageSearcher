package com.shlyankin.navigation

sealed class AppScreen {
    object MainScreen : AppScreen()
    class ViewPhoto(val photoId: String) : AppScreen()
}