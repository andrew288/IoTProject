package com.ghostsoftware.iotproject.data.network.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ghostsoftware.iotproject.models.SensorDataEntity


@Database(entities = [SensorDataEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun sensorDataEntityDao(): SensorDataEntityDao
}