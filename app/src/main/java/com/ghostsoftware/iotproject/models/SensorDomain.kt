package com.ghostsoftware.iotproject.models

import com.ghostsoftware.iotproject.data.local.entity.SensorDataEntity
import com.google.gson.Gson


data class SensorDomain(
    val id: Int,
    val date: String,
    val time: String,
    val value: Int,
    val unit: String,
    val notes: String
){
    companion object {
        fun fromJson(json: String): SensorDomain? {
            return try{
                val gson = Gson()
                gson.fromJson(json, SensorDomain::class.java)
            } catch (e: Exception){
                e.printStackTrace()
                null
            }
        }
    }
}
fun SensorDomain.toSensorDataEntity(): SensorDataEntity = SensorDataEntity(id=id,date=date,time=time,value=value,unit=unit,notes=notes)
fun SensorDataEntity.toSensorDomain(): SensorDomain = SensorDomain(id=id,date=date,time=time,value=value,unit=unit,notes=notes)