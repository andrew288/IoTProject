package com.ghostsoftware.iotproject.models

import com.google.gson.Gson

data class SensorLuminosidad(
    val data: Float,
    val datetime: String,
){
    companion object {
        fun fromJson(json: String): SensorLuminosidad? {
            return try{
                val gson = Gson()
                gson.fromJson(json, SensorLuminosidad::class.java)
            } catch (e: Exception){
                e.printStackTrace()
                null
            }
        }
    }
}
