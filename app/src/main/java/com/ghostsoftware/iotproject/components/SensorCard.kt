package com.ghostsoftware.iotproject.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ghostsoftware.iotproject.models.SensorDomain

@Composable
fun SensorCard(
    modifier: Modifier = Modifier,
    sensorDomain: SensorDomain,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .shadow(4.dp, shape = RoundedCornerShape(8.dp))
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Date : ",
                    fontWeight = FontWeight.Bold
                )
                Text(text = sensorDomain.date)
                Text(
                    text = "Time : ",
                    fontWeight = FontWeight.Bold
                )
                Text(text = sensorDomain.time)
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = "Value : ",
                    fontWeight = FontWeight.Bold
                )
                Text(text = "${sensorDomain.value} ${sensorDomain.unit}")
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Notes : ${sensorDomain.notes}",
                fontWeight = FontWeight.Bold
            )
        }
    }
}