package com.ghostsoftware.iotproject.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun AccountScreen(navController: NavHostController, navControllerApp: NavHostController) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "ACCOUNT SCREEN")
    }
}