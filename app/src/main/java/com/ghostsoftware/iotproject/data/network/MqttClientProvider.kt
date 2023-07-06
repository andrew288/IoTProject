package com.ghostsoftware.iotproject.data.network

import org.eclipse.paho.client.mqttv3.MqttClient

interface MqttClientProvider {
    fun provideMqttClient(): MqttClient
}