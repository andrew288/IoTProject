package com.ghostsoftware.iotproject.uis.main.screen

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.ghostsoftware.iotproject.components.BottomNavigationBar
import com.ghostsoftware.iotproject.navigation.bottomBar.BottomBarScreen
import com.ghostsoftware.iotproject.navigation.bottomBar.BottomNavGraph
import com.ghostsoftware.iotproject.uis.main.screen.screen_home.HomeViewModel
import com.ghostsoftware.iotproject.uis.main.screen.screen_sensor.SensorDataViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navControllerApp: NavHostController,
    homeViewModel: HomeViewModel,
    sensorDataViewModel: SensorDataViewModel
) {
    val navController = rememberNavController()
    val navigationItems = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Horarios,
        BottomBarScreen.Sensor,
        BottomBarScreen.Account
    )

    Scaffold(bottomBar = { BottomNavigationBar(navController = navController, items = navigationItems) }){
        BottomNavGraph(navController = navController, navControllerApp, homeViewModel, sensorDataViewModel)
    }
}