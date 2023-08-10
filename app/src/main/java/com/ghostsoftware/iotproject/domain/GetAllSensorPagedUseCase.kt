package com.ghostsoftware.iotproject.domain


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.ghostsoftware.iotproject.data.local.SensorDataEntityRepository
import com.ghostsoftware.iotproject.data.local.entity.SensorDataEntity
import com.ghostsoftware.iotproject.models.SensorDomain
import com.ghostsoftware.iotproject.models.toSensorDomain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

import javax.inject.Inject


class GetAllSensorPagedUseCase @Inject constructor(
    private val sensorDataEntityRepository: SensorDataEntityRepository

) {
     operator fun invoke(): Flow<PagingData<SensorDomain>> = Pager(
        PagingConfig(
            pageSize = 10,
            prefetchDistance =20
        )
    ){
         sensorDataEntityRepository.getAllSensorPaging()
    }
        .flow.map {
            value: PagingData<SensorDataEntity> ->
            value.map {
                sensor : SensorDataEntity ->
                sensor.toSensorDomain()
            }

    }
}