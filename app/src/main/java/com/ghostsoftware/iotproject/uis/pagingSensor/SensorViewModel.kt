package com.ghostsoftware.iotproject.uis.pagingSensor




import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ghostsoftware.iotproject.data.network.ClientMQTT
import com.ghostsoftware.iotproject.domain.GetAllSensorPagedUseCase
import com.ghostsoftware.iotproject.domain.GetSensors
import com.ghostsoftware.iotproject.domain.SensorDomain
import com.ghostsoftware.iotproject.domain.AddSensor


import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class SensorViewModel @Inject constructor(
    private val getSensors: GetSensors,
    private  val addSensor: AddSensor,
    private val getAllSensorPagedUseCase: GetAllSensorPagedUseCase,
    private val clientMQTT: ClientMQTT
) : ViewModel(){

    private val _state: MutableState<SensorState> = mutableStateOf(SensorState())
    private val _state2: MutableState<SensorPagingState> = mutableStateOf(SensorPagingState())
    val state : State<SensorState> get() = _state
    val state2: State<SensorPagingState> get() = _state2

    init {

        collectPagingSensors()
    }

    fun subscribeTopicSensor() {
        if (clientMQTT.isConnected()) {
            clientMQTT.subscribe("sensor/data", 0) { data ->
            }
        }
    }

    private fun collectPagingSensors(){
        viewModelScope.launch(Dispatchers.IO) {
            val fetchSensors  = getAllSensorPagedUseCase().cachedIn(viewModelScope)
            Log.d("GET_ALL_SENSORS:",fetchSensors.toString())
            withContext(Dispatchers.Main){
                _state2.value = _state2.value.copy(sensors = fetchSensors)
            }
        }
    }

    fun insertSensor(sensorDomain: SensorDomain){
        viewModelScope.launch(Dispatchers.IO) {
            addSensor(sensorDomain)
        }
    }


}