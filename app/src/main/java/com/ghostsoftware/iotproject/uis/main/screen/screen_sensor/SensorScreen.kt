package com.ghostsoftware.iotproject.uis.main.screen.screen_sensor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.ghostsoftware.iotproject.R
import com.himanshoe.charty.common.ChartDataCollection
import com.himanshoe.charty.common.config.AxisConfig
import com.himanshoe.charty.line.CurveLineChart
import com.himanshoe.charty.line.model.LineData

@Composable
fun SensorScreen(navController: NavHostController, sensorViewModel: SensorDataViewModel) {
    val datosSensor: List<LineData> = sensorViewModel.dataList
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
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = colorResource(id = R.color.green_jade),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Actualizar conexión")
            }
            Spacer(modifier = Modifier.height(20.dp))
            if (datosSensor.size != 0) {
                LinearChart(datosSensor)
            }
        }
    }
}

@Composable
fun LinearChart(datosSensor: List<LineData>) {
    CurveLineChart(
        dataCollection = ChartDataCollection(datosSensor),
        modifier = Modifier
            .padding(start = 12.dp, top = 10.dp)
            .height(300.dp),
        padding = 15.dp,
        radiusScale = 0.01f,
        axisConfig = AxisConfig(
            minLabelCount = 6,
            showAxes = true,
            showGridLabel = true,
            showGridLines = false,
            axisColor = Color.Gray,
            axisStroke = 1.0f
        ),

        )
}