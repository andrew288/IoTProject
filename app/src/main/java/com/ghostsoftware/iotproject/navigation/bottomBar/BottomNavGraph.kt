package com.ghostsoftware.iotproject.navigation.bottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ghostsoftware.iotproject.screens.main.AccountScreen
import com.ghostsoftware.iotproject.screens.main.HomeScreen
import com.ghostsoftware.iotproject.screens.main.HorarioScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    navControllerApp: NavHostController,
){
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route){

        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navController)
        }
        composable(route= BottomBarScreen.Horarios.route){
            HorarioScreen(navController)
        }
        composable(route = BottomBarScreen.Account.route){
            AccountScreen(navController, navControllerApp)
        }
    }
}