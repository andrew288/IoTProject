package com.ghostsoftware.iotproject.uis.pagingSensor

import com.ghostsoftware.iotproject.domain.SensorDomain


data class SensorState (
    var sensors : List<SensorDomain> = emptyList()
)