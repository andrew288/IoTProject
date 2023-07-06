package com.ghostsoftware.iotproject.client

import org.eclipse.paho.client.mqttv3.MqttClient

interface MqttClientProvider {
    fun provideMqttClient(): MqttClient
}