package com.ghostsoftware.iotproject.navigation.bottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ghostsoftware.iotproject.uis.main.screen.AccountScreen
import com.ghostsoftware.iotproject.uis.main.screen.HomeScreen
import com.ghostsoftware.iotproject.uis.main.screen.HorarioScreen
import com.ghostsoftware.iotproject.uis.main.screen.SensorScreen
import com.ghostsoftware.iotproject.uis.main.SensorViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    navControllerApp: NavHostController,
    sensorViewModel: SensorViewModel,
){
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route){

        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navController, sensorViewModel)
        }
        composable(route= BottomBarScreen.Sensor.route){
            SensorScreen(navController, sensorViewModel)
        }
        composable(route= BottomBarScreen.Horarios.route){
            HorarioScreen(navController)
        }
        composable(route = BottomBarScreen.Account.route){
            AccountScreen(navController, navControllerApp)
        }
    }
}