package com.shlyankin.util.navigation

import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.shlyankin.util.BindingFragment
import com.shlyankin.util.utils.observe

abstract class NavigableBindingFragment<B : ViewBinding> : BindingFragment<B>() {

    protected abstract val navigationViewModel: NavigationViewModel
    protected open val navController: NavController by lazy { findNavController() }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(navigationViewModel.navigateEvent) {
            handleNavigation(it)
        }
    }

    protected open fun handleNavigation(navInfo: NavigationInfo) {
        when (navInfo) {
            is NavigationInfo.NavigationTo -> {
                navController.navigate(navInfo.destionationId, navInfo.bundle)
            }
            is NavigationInfo.NavigationPopTo -> {
                navController.popBackStack(navInfo.destionationId, false)
            }
            is NavigationInfo.NavigationPop -> {
                if (!navController.popBackStack()) {
                    navigationViewModel.onLastFragmentInBackStackTriedToClose()
                }
            }
        }
    }
}