package com.ghostsoftware.iotproject.uis.main.screen.pagingSensor

import com.ghostsoftware.iotproject.models.SensorDomain


data class SensorState (
    var sensors : List<SensorDomain> = emptyList()
)