package com.ghostsoftware.iotproject.data.local

import androidx.paging.PagingSource
import com.ghostsoftware.iotproject.data.local.entity.SensorDataEntity
import javax.inject.Inject

class SensorDataEntityRepository @Inject constructor(
    private val sensorDao: SensorDataEntityDao
) {

    fun getAllSensor(): List<SensorDataEntity>{
        return sensorDao.getAll()
    }

    fun getAllSensorPaging(): PagingSource<Int, SensorDataEntity>{
        return sensorDao.getAllPaging()
    }
    fun insertSensor(sensor: SensorDataEntity){
        sensorDao.insert(sensor)
    }

    fun updateSensor(sensor: SensorDataEntity){
        sensorDao.update(sensor)
    }

    fun deleteSensor(sensor: SensorDataEntity){
        sensorDao.delete(sensor)
    }
}