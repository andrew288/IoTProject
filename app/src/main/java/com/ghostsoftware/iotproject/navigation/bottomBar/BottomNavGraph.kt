package com.ghostsoftware.iotproject.navigation.bottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.ghostsoftware.iotproject.uis.main.screen.AccountScreen
import com.ghostsoftware.iotproject.uis.main.screen.screen_home.HomeScreen
import com.ghostsoftware.iotproject.uis.main.screen.HorarioScreen
import com.ghostsoftware.iotproject.uis.main.screen.screen_sensor.SensorScreen
import com.ghostsoftware.iotproject.uis.main.screen.screen_home.HomeViewModel
import com.ghostsoftware.iotproject.uis.main.screen.screen_sensor.SensorDataViewModel

@Composable
fun BottomNavGraph(
    navController: NavHostController,
    navControllerApp: NavHostController,
    homeViewModel: HomeViewModel,
    sensorDataViewModel: SensorDataViewModel,
){
    NavHost(navController = navController, startDestination = BottomBarScreen.Home.route){

        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navController, homeViewModel)
        }
        composable(route= BottomBarScreen.Sensor.route){
            SensorScreen(navController, sensorDataViewModel)
        }
        composable(route= BottomBarScreen.Horarios.route){
            HorarioScreen(navController)
        }
        composable(route = BottomBarScreen.Account.route){
            AccountScreen(navController, navControllerApp)
        }
    }
}