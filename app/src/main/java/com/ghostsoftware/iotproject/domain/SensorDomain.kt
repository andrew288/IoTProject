package com.ghostsoftware.iotproject.domain

import androidx.room.PrimaryKey
import com.ghostsoftware.iotproject.models.SensorDataEntity


data class SensorDomain(
    val id: Int,
    val date: String,
    val time: String,
    val value: Int,
    val unit: String,
    val notes: String
)
fun SensorDomain.toSensorDataEntity(): SensorDataEntity = SensorDataEntity(id=id,date=date,time=time,value=value,unit=unit,notes=notes)
fun SensorDataEntity.toSensorDomain():SensorDomain = SensorDomain(id=id,date=date,time=time,value=value,unit=unit,notes=notes)