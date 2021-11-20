package com.shlyankin.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen("MainScreen")
    object ViewPhoto : Screen("ViewPhoto")
}