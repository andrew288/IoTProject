package com.ghostsoftware.iotproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.ghostsoftware.iotproject.navigation.AppNavHost
import com.ghostsoftware.iotproject.uis.main.SensorViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val sensorViewModel:SensorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppNavHost(sensorViewModel)
        }
    }
}