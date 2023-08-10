package com.ghostsoftware.iotproject.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ghostsoftware.iotproject.domain.SensorDomain


@Composable
fun SensorCard(
    modifier: Modifier = Modifier,
    sensorDomain: SensorDomain,

) {

    Card(

    ) {
        Column (modifier = Modifier.fillMaxWidth()) {
            Text(text = "Date : "+sensorDomain.date)
            Text(text = "Time : "+sensorDomain.time)
            Text(text = "Value : "+sensorDomain.value.toString())
            Text(text = "Unit : "+sensorDomain.unit)
            Text(text = "Notes : "+sensorDomain.notes)
        }
    }


}