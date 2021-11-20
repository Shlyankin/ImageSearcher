package com.shlyankin.imagesearcher

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun ViewPhotoScreen(navController: NavController) {
    Button(onClick = {
        navController.popBackStack()
    }, content = {
        Text("Back")
    })
}