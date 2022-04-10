package com.shlyankin.util.navigation

import androidx.lifecycle.LiveData

interface NavigationViewModel {

    fun onLastFragmentInBackStackTriedToClose() = Unit

    val navigateEvent: LiveData<NavigationInfo>
}