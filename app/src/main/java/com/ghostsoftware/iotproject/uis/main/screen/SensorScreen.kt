package com.ghostsoftware.iotproject.uis.main.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ghostsoftware.iotproject.uis.main.SensorViewModel

@Composable
fun SensorScreen(navController: NavHostController, sensorViewModel: SensorViewModel) {
    val datosSensor: List<String> = sensorViewModel.dataList
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "SENSOR SCREEN",
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally),
            )
            Spacer(modifier = Modifier.height(20.dp))
            OutlinedButton(
                onClick = { sensorViewModel.subscribeTopicSensor() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Actualizar conexión")
            }
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn(
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(datosSensor) { item ->
                    Text(text = item, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}