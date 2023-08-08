package com.ghostsoftware.iotproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ghostsoftware.iotproject.uis.auth.screen.LoginScreen
import com.ghostsoftware.iotproject.uis.main.screen.MainScreen
import com.ghostsoftware.iotproject.uis.auth.screen.RegisterScreen
import com.ghostsoftware.iotproject.uis.StartScreen
import com.ghostsoftware.iotproject.uis.main.screen.screen_home.HomeViewModel
import com.ghostsoftware.iotproject.uis.main.screen.screen_sensor.SensorDataViewModel

@Composable
fun AppNavHost(
    homeViewModel: HomeViewModel,
    sensorDataViewModel: SensorDataViewModel,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.ScreenMain.route
){
    NavHost(navController = navController, startDestination = startDestination){
        composable(Routes.ScreenLogin.route){
            LoginScreen(navController)
        }
        composable(Routes.ScreenRegister.route){
            RegisterScreen(navController)
        }
        composable(Routes.ScreenMain.route){
            MainScreen(navController, homeViewModel, sensorDataViewModel)
        }
        composable(Routes.ScreenStart.route){
            StartScreen(navController)
        }
    }
}