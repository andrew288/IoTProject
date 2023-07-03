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
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext

class ClientMQTT(
    context: Context,
    serverUri: String,
    clientId: String
) {
    private val mqttClient: MqttClient

    init {
        // Cargar el certificado de cliente desde el archivo
        val certificateFactory = CertificateFactory.getInstance("X.509")
        val certificateInputStream: InputStream = context.assets.open("cert.der")
        val certificate = certificateFactory.generateCertificate(certificateInputStream) as X509Certificate

        // Cargar la clave privada desde el archivo
        val keyInputStream: InputStream = context.assets.open("private.der")
        val privateKeyBytes = keyInputStream.readBytes()
        val keyFactory = KeyFactory.getInstance("RSA")
        val privateKeySpec = PKCS8EncodedKeySpec(privateKeyBytes)
        val privateKey: PrivateKey = keyFactory.generatePrivate(privateKeySpec)

        // Crear el contexto SSL
        val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
        keyStore.load(null)
        keyStore.setCertificateEntry("alias", certificate)
        keyStore.setKeyEntry("alias", privateKey, null, arrayOf(certificate))

        val sslContext = SSLContext.getInstance("TLSv1.2")
        val keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        keyManagerFactory.init(keyStore, null)
        sslContext.init(keyManagerFactory.keyManagers, null, null)

        // Configurar las opciones de conexión MQTT
        val options = MqttConnectOptions()
        options.socketFactory = sslContext.socketFactory

        // Crear el cliente MQTT y establecer la conexión
        mqttClient = MqttClient(serverUri, clientId, MemoryPersistence())
        mqttClient.connect(options)
    }

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