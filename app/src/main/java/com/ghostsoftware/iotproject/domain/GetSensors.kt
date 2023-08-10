package com.ghostsoftware.iotproject.domain


import com.ghostsoftware.iotproject.data.local.SensorDataEntityRepository
import com.ghostsoftware.iotproject.models.SensorDomain
import com.ghostsoftware.iotproject.models.toSensorDomain
import javax.inject.Inject

class GetSensors @Inject constructor(
    private val sensorDataEntityRepository: SensorDataEntityRepository
) {
    operator fun invoke(): List<SensorDomain>{
        return sensorDataEntityRepository.getAllSensor().map {
            it.toSensorDomain()
        }
    }
}