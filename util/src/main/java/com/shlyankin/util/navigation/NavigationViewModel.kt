package com.shlyankin.util.navigation

import kotlinx.coroutines.flow.Flow

interface NavigationViewModel {

    fun onLastFragmentInBackStackTriedToClose() = Unit

    val navigateEvent: Flow<NavigationInfo>
}