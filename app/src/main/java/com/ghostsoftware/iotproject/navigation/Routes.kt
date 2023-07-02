package com.ghostsoftware.iotproject.navigation

sealed class Routes(val route: String) {
    object ScreenMain: Routes("MainScreen")
    object ScreenLogin: Routes("LoginScreen")
    object ScreenStart: Routes("StartScreen")
    object ScreenRegister: Routes("RegisterScreen")
}