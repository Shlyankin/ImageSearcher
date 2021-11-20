package com.shlyankin.imagesearcher.ui

import android.Manifest
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.shlyankin.imagesearcher.MainScreen
import com.shlyankin.navigation.Screen
import com.shlyankin.util.navigation.NavigationViewModel
import com.shlyankin.util.utils.getNonGrantedPermissions
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private companion object {
        const val PERMISSIONS_CODE = 1703
    }

    private val navigationViewModel: NavigationViewModel by viewModels<MainNavigationViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
                composable(Screen.MainScreen.route) {
                    MainScreen(navController)
                }
                composable(Screen.ViewPhoto.route) {

                }
            }
        }
        checkPermissions()
    }

    private fun checkPermissions() {
        val permissions = arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val nonGrantedPermissions = getNonGrantedPermissions(*permissions)
        if (nonGrantedPermissions.isNotEmpty()) {
            requestPermissions(nonGrantedPermissions, PERMISSIONS_CODE)
        }
    }
}