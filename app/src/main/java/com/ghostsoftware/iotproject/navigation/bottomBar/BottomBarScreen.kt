package com.ghostsoftware.iotproject.navigation.bottomBar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
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
        icon = Icons.Default.DateRange
    )

    object Sensor: BottomBarScreen(
        route = "SENSOR",
        title = "Sensor",
        icon = Icons.Default.Notifications
    )

    object Account: BottomBarScreen(
        route = "HISTORICO",
        title = "Historico",
        icon = Icons.Default.List
    )
}
