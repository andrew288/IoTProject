package com.ghostsoftware.iotproject.uis.main.screen.screen_sensor

import androidx.compose.runtime.mutableStateListOf
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
    private val _alert = MutableLiveData<String>("No datos")
    val alert: LiveData<String> = _alert

    fun subscribeTopicSensor() {
        if (clientMQTT.isConnected()) {
            clientMQTT.subscribe("sensor/data", 0) { data ->
                val sensorLuminosidad = SensorLuminosidad.fromJson(data)
                if (sensorLuminosidad != null) {
                    _dataList.add(LineData(sensorLuminosidad.value, sensorLuminosidad.time))
                    if (_dataList.size >= 8) {
                        _dataList.removeAt(1)
                    }
                    // determinar información de ambiente
                    _alert.value = determinarAlerta(sensorLuminosidad.value)
                }
            }
        }
    }

    fun determinarAlerta(sensorValue: Float): String{
        var valueAlert = ""
        if(sensorValue > 50.0f){
            valueAlert = "Ambiente oscuro"
        } else {
            valueAlert = "Ambiente claro"
        }
        return valueAlert
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