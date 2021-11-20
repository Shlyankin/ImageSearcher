package com.shlyankin.navigation

sealed class InnerMainScreen(route: String) : Screen(route) {

    companion object {
        val items = listOf(PhotosList, FavouritePhotosList)
    }

    object PhotosList : InnerMainScreen("PhotosList")
    object FavouritePhotosList : InnerMainScreen("FavouritePhotosList")
}