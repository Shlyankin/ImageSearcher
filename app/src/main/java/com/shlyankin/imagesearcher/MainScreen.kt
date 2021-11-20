package com.shlyankin.imagesearcher

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Photo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.shlyankin.navigation.InnerMainScreen

@Composable
fun MainScreen(navController: NavController) {
    val innerNavController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                InnerMainScreen.items.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Photo, contentDescription = null) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            innerNavController,
            startDestination = InnerMainScreen.PhotosList.route,
            Modifier.padding(innerPadding)
        ) {
            composable(InnerMainScreen.PhotosList.route) { PhotosListScreen(navController) }
            composable(InnerMainScreen.FavouritePhotosList.route) {
                FavouritePhotosListScreen(
                    navController
                )
            }
        }
    }
}