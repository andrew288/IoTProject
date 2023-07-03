package com.ghostsoftware.iotproject.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ghostsoftware.iotproject.client.ClientMQTT
import com.ghostsoftware.iotproject.screens.LoginScreen
import com.ghostsoftware.iotproject.screens.MainScreen
import com.ghostsoftware.iotproject.screens.RegisterScreen
import com.ghostsoftware.iotproject.screens.StartScreen

@Composable
fun AppNavHost(
    clientMQTT: ClientMQTT,
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
            MainScreen(navController, clientMQTT)
        }
        composable(Routes.ScreenStart.route){
            StartScreen(navController)
        }
    }
}