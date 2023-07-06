package com.ghostsoftware.iotproject.client

import android.content.Context
import org.eclipse.paho.android.service.MqttAndroidClient
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import java.io.InputStream
import java.nio.charset.StandardCharsets
import java.security.KeyFactory
import java.security.KeyStore
import java.security.PrivateKey
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.security.spec.PKCS8EncodedKeySpec
import javax.inject.Inject
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext

class ClientMQTT @Inject constructor(
    private val mqttClientProvider: MqttClientProvider
) {
    private val mqttClient = mqttClientProvider.provideMqttClient()
    fun publish(topic: String, message: String) {
        val mqttMessage = MqttMessage(message.toByteArray())
        mqttClient.publish(topic, mqttMessage)
    }

    fun disconnect() {
        mqttClient.disconnect()
    }

    fun subscribe(topic: String, qos: Int, onDataReceived: (String) -> Unit) {
        mqttClient.subscribe(topic, qos) { _, message ->
            val data = message.payload.toString(StandardCharsets.UTF_8)
            onDataReceived(data)
        }
    }

    fun unsubscribe(topic: String){
        mqttClient.unsubscribe(topic)
    }
}