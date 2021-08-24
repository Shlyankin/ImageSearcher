package com.shlyankin.navigation

sealed class AppScreen {
    class ViewPhoto(val photoId: String) : AppScreen()
}