package com.ghostsoftware.iotproject.uis.main.screen.screen_sensor

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ghostsoftware.iotproject.data.network.ClientMQTT
import com.ghostsoftware.iotproject.models.SensorLuminosidad
import com.himanshoe.charty.line.model.LineData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SensorDataViewModel @Inject constructor(
    private val clientMQTT: ClientMQTT
) : ViewModel() {
    private val _dataList = mutableStateListOf(LineData(0f, "00:00"))
    val dataList: List<LineData> get() = _dataList
    private var _alert = MutableLiveData("No datos")
    val alert: LiveData<String> = _alert
    private var _colorEnviroment = MutableLiveData(Color(0xFFFFFFFF))
    val colorEnviroment: MutableLiveData<Color> = _colorEnviroment

    fun subscribeTopicSensor() {
        if (clientMQTT.isConnected()) {
            clientMQTT.subscribe("sensor/data", 0) { data ->
                val sensorLuminosidad = SensorLuminosidad.fromJson(data)
                if (sensorLuminosidad != null) {
                    // determinamos alerta
                    _alert.postValue(determinarAlerta(sensorLuminosidad.value))
                    // determinamos color
                    println("color_enviroment" + determinarColor(sensorLuminosidad.value))
                    _colorEnviroment.postValue(determinarColor(sensorLuminosidad.value))
                    // añadimos punto al grafico
                    _dataList.add(LineData(sensorLuminosidad.value, sensorLuminosidad.time))
                    if (_dataList.size >= 8) {
                        _dataList.removeAt(1)
                    }
                }
            }
        }
    }

    fun determinarAlerta(sensorValue: Float): String {
        return when {
            sensorValue <= 63 -> "Ambiente con baja luminosidad"
            sensorValue <= 127 -> "Ambiente oscuro"
            sensorValue <= 191 -> "Ambiente con sombras"
            sensorValue <= 223 -> "Ambiente iluminado"
            else -> "Ambiente con alta luminosidad"
        }
    }
    fun determinarColor(sensorValue: Float): Color {
        return when {
            sensorValue <= 63 -> Color(0xFF333333)
            sensorValue <= 127 -> Color(0xFF666666)
            sensorValue <= 191 -> Color(0xFF999999)
            sensorValue <= 223 -> Color(0xFFFFFFBF)
            else -> Color(0xFFFFFF66)
        }
    }
    override fun onCleared() {
        // Eliminar la suscripción cuando el ViewModel sea destruido
        val topic = "sensor/data"
        if (clientMQTT.isConnected()) {
            clientMQTT.unsubscribe(topic)
        }
        super.onCleared()
    }
}