package com.ghostsoftware.iotproject.data.network.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.ghostsoftware.iotproject.models.SensorDataEntity


@Dao
interface SensorDataEntityDao {

    @Query("SELECT * FROM SensorDataEntity")
    fun getAll(): List<SensorDataEntity>

    @Query("SELECT * FROM SensorDataEntity")
    fun getAllPaging(): PagingSource<Int,SensorDataEntity>

    @Query("SELECT * FROM SensorDataEntity WHERE id = :sensorId")
    fun getById(sensorId: Int): SensorDataEntity

    @Insert
    fun insert(sensor: SensorDataEntity)

    @Update
    fun update(sensor: SensorDataEntity)

    @Delete
    fun delete(sensor: SensorDataEntity)
}