package com.ghostsoftware.iotproject.uis.main.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ghostsoftware.iotproject.uis.main.SensorViewModel

@Composable
fun HomeScreen(navController: NavHostController, sensorViewModel: SensorViewModel) {

    var buttonColor1 = remember {
        mutableStateOf(Color.Gray)
    }
    var buttonColor2 = remember {
        mutableStateOf(Color.Gray)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "HOME SCREEN")
        Spacer(modifier = Modifier.height(20.dp))
        Row() {
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor1.value,
                    contentColor = Color.Black
                ),
                onClick = {
                    buttonColor1.value = Color.Green
                    buttonColor2.value = Color.Gray
                    sensorViewModel.publishMessage("on_off", "1")
                }) {
                Text(text = "ON")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = buttonColor2.value,
                    contentColor = Color.Black
                ),
                onClick = {
                    buttonColor1.value = Color.Gray
                    buttonColor2.value = Color.Red
                    sensorViewModel.publishMessage("on_off", "0")
                }) {
                Text(text = "OFF")
            }
        }

    }

}