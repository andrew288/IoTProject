package com.ghostsoftware.iotproject.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ghostsoftware.iotproject.client.ClientMQTT

@Composable
fun SensorScreen(navController: NavHostController, clientMQTT: ClientMQTT) {

    val datosSensor = remember {
        mutableStateListOf<String>()
    }
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

    clientMQTT.subscribe("outTopic", 0){
        datosSensor += it
    }


}