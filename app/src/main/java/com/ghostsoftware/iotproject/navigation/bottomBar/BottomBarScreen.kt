package com.ghostsoftware.iotproject.navigation.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: ImageVector
){
    object Home: BottomBarScreen(
        route = "HOME",
        title = "Home",
        icon = Icons.Default.Home
    )

    object Horarios: BottomBarScreen(
        route = "HORARIOS",
        title = "Horarios",
        icon = Icons.Default.Notifications
    )

    object Account: BottomBarScreen(
        route = "ACCOUNT",
        title = "Account",
        icon = Icons.Default.AccountCircle
    )
}
