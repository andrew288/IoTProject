package com.ghostsoftware.iotproject.uis.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.ghostsoftware.iotproject.data.network.ClientMQTT
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SensorViewModel @Inject constructor(
    private val clientMQTT: ClientMQTT
) : ViewModel() {
    private val _dataList = mutableStateListOf<String>()
    val dataList: List<String> get() = _dataList

    fun subscribeTopicSensor() {
        if (clientMQTT.isConnected()) {
            clientMQTT.subscribe("sensor/data", 0) { data ->
                _dataList.add(data)
            }
        }
    }
    fun publishMessage(topic: String, message: String) {
        if (clientMQTT.isConnected()) {
            clientMQTT.publish(topic, message)
        }
    }
    override fun onCleared() {
        super.onCleared()
        // Eliminar la suscripción cuando el ViewModel sea destruido
        val topic = "sensor/data"
        if (clientMQTT.isConnected()) {
            println("PASO POR LA UNSUBSCRIBE")
            clientMQTT.unsubscribe(topic)
        }
    }
}