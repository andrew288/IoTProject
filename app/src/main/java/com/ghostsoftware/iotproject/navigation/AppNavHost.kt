package com.ghostsoftware.iotproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ghostsoftware.iotproject.uis.main.screen.MainScreen
import com.ghostsoftware.iotproject.uis.StartScreen
import com.ghostsoftware.iotproject.uis.main.screen.screen_home.HomeViewModel
import com.ghostsoftware.iotproject.uis.main.screen.screen_sensor.SensorDataViewModel
import com.ghostsoftware.iotproject.uis.main.screen.pagingSensor.SensorViewModel

@Composable
fun AppNavHost(
    homeViewModel: HomeViewModel,
    sensorDataViewModel: SensorDataViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.ScreenMain.route,
    sensorViewModel: SensorViewModel
){
    NavHost(navController = navController, startDestination = startDestination){
        composable(Routes.ScreenMain.route){
            MainScreen(homeViewModel, sensorDataViewModel, sensorViewModel)
        }
        composable(Routes.ScreenStart.route){
            StartScreen(navController)
        }
    }
}