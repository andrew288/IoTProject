package com.ghostsoftware.iotproject.uis.pagingSensor

import androidx.paging.PagingData
import com.ghostsoftware.iotproject.domain.SensorDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class SensorPagingState (
    var sensors : Flow<PagingData<SensorDomain>> = emptyFlow()
)