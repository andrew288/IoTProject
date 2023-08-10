package com.ghostsoftware.iotproject.uis.main.screen.screen_sensor

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ghostsoftware.iotproject.R
import com.himanshoe.charty.common.ChartDataCollection
import com.himanshoe.charty.common.config.AxisConfig
import com.himanshoe.charty.line.CurveLineChart
import com.himanshoe.charty.line.model.LineData

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SensorScreen(sensorViewModel: SensorDataViewModel) {
    val dataSensor: List<LineData> = sensorViewModel.dataList
    val alertSensor: String by sensorViewModel.alert.observeAsState("No information")
    val colorEnvironment: Color by sensorViewModel.colorEnviroment.observeAsState(Color(0xFFFFFFFF))

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Real time") },
                backgroundColor = Color.White
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
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
            if (dataSensor.isNotEmpty()) {
                LinearChart(dataSensor)
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = alertSensor, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        // Representación del círculo de color
                        Spacer(
                            modifier = Modifier
                                .size(80.dp)
                                .background(colorEnvironment, shape = CircleShape)
                                .border(1.dp, Color.Gray, CircleShape)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LinearChart(dataSensor: List<LineData>) {
    CurveLineChart(
        dataCollection = ChartDataCollection(dataSensor),
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