package com.ghostsoftware.iotproject.models

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity
data class SensorDataEntity (
    @PrimaryKey(autoGenerate = true)  val id: Int=0,
    val date: String,
    val time: String,
    val value: Int,
    val unit: String,
    val notes: String
)