package com.shlyankin.imagesearcher.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.shlyankin.imagesearcher.databinding.ActivityMainBinding
import com.shlyankin.util.navigation.NavigationInfo
import com.shlyankin.util.navigation.NavigationViewModel
import com.shlyankin.util.utils.getNonGrantedPermissions
import com.shlyankin.util.utils.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private companion object {
        const val PERMISSIONS_CODE = 1703
    }

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val navigationViewModel: NavigationViewModel by viewModels<MainNavigationViewModel>()
    private val navController: NavController by lazy { findNavController(binding.rootContainer.id) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        observe(navigationViewModel.navigateEvent) {
            handleNavigation(it)
        }
        checkPermissions()
    }

    private fun handleNavigation(navInfo: NavigationInfo) {
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

    private fun checkPermissions() {
        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val nonGrantedPermissions = getNonGrantedPermissions(*permissions)
        if (nonGrantedPermissions.isNotEmpty()) {
            requestPermissions(nonGrantedPermissions, PERMISSIONS_CODE)
        }
    }
}