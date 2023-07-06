package com.ghostsoftware.iotproject.uis.main.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ghostsoftware.iotproject.uis.main.SensorViewModel

@Composable
fun SensorScreen(navController: NavHostController, sensorViewModel: SensorViewModel) {

    val datosSensor:List<String> = sensorViewModel.dataList
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "SENSOR SCREEN")
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn(){
            items(datosSensor){
                item ->
                Text(text = item, modifier = Modifier.padding(16.dp))
            }
        }
    }
}