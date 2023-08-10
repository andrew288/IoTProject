
package com.ghostsoftware.iotproject.domain
import com.ghostsoftware.iotproject.data.network.local.SensorDataEntityRepository

import javax.inject.Inject

class AddSensor @Inject constructor(
    private val sensorDataEntityRepository: SensorDataEntityRepository
){
     operator fun invoke(sensorDomain: SensorDomain){
         sensorDataEntityRepository.insertSensor(sensorDomain.toSensorDataEntity())
    }
}