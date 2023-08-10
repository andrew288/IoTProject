
package com.ghostsoftware.iotproject.domain
import com.ghostsoftware.iotproject.data.local.SensorDataEntityRepository
import com.ghostsoftware.iotproject.models.SensorDomain
import com.ghostsoftware.iotproject.models.toSensorDataEntity

import javax.inject.Inject

class AddSensor @Inject constructor(
    private val sensorDataEntityRepository: SensorDataEntityRepository
){
     operator fun invoke(sensorDomain: SensorDomain){
         sensorDataEntityRepository.insertSensor(sensorDomain.toSensorDataEntity())
    }
}