package com.ghostsoftware.iotproject

import android.util.Log
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.ghostsoftware.iotproject.client.ClientMQTT

class DataSensorViewModel(
    private val clientMQTT: ClientMQTT
): ViewModel() {
    private val TAG = "DatosSensorViewModel"
    private val _dataList = mutableStateListOf<String>()
    val dataList: List<String> get() = _dataList

    init {
        // Realizar la suscripción al tema deseado
        val topic = "sensor/data"
        val qos = 1
        clientMQTT.subscribe(topic, qos) { data ->
            Log.d(TAG, "Received data: $data")
            _dataList.add(data)
        }
    }

    override fun onCleared() {
        super.onCleared()
        // Eliminar la suscripción cuando el ViewModel sea destruido
        val topic = "sensor/data"
        clientMQTT.unsubscribe(topic)
    }
}