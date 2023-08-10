package com.ghostsoftware.iotproject.uis.pagingSensor



import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.ghostsoftware.iotproject.components.SensorCard
import com.ghostsoftware.iotproject.domain.SensorDomain



@Composable
fun PagingSensorScreen(
    sensorViewModel: SensorViewModel
    ){



    val sensorLazyPagingItems: LazyPagingItems<SensorDomain> = sensorViewModel.state2.value.sensors.collectAsLazyPagingItems()
    SensorOverViewScreenContent(sensorLazyPagingItems=sensorLazyPagingItems)

}

@Composable
private fun SensorOverViewScreenContent(
    sensorLazyPagingItems: LazyPagingItems<SensorDomain>,
){
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 16.dp) ){
      items(
          count = sensorLazyPagingItems.itemCount,
          key = sensorLazyPagingItems.itemKey{sensor -> sensor.id},
          contentType = sensorLazyPagingItems.itemContentType{"Sensores"}
      )  {
          index : Int ->
          val sensorDomain: SensorDomain? = sensorLazyPagingItems[index]

          if(sensorDomain != null){
              SensorCard(sensorDomain = sensorDomain)
          }
      }
    }

}
