package com.ghostsoftware.iotproject.uis.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.ghostsoftware.iotproject.client.ClientMQTT
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SensorViewModel @Inject constructor(
    private val clientMQTT: ClientMQTT
) : ViewModel() {
    private val _dataList = mutableStateListOf<String>()
    val dataList: List<String> get() = _dataList

    init {
        // Realizar la suscripción al tema deseado
        val topic = "sensor/data"
        val qos = 1
        clientMQTT.subscribe(topic, qos) { data ->
            _dataList.add(data)
        }
    }

    fun publishMessage(topic: String, q0s: String) {
        clientMQTT.publish(topic, q0s)
    }

    override fun onCleared() {
        super.onCleared()
        // Eliminar la suscripción cuando el ViewModel sea destruido
        val topic = "sensor/data"
        clientMQTT.unsubscribe(topic)
    }
}