package com.ghostsoftware.iotproject.data.network

import android.content.Context
import android.util.Log
import android.widget.Toast
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
import org.eclipse.paho.client.mqttv3.MqttException
import org.eclipse.paho.client.mqttv3.MqttMessage
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence
import java.io.InputStream
import java.security.KeyFactory
import java.security.KeyStore
import java.security.PrivateKey
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.security.spec.PKCS8EncodedKeySpec
import java.util.UUID
import javax.inject.Inject
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext

class MqttClientProvideImpl @Inject constructor(private val context: Context) : MqttClientProvider {

    companion object {
        private val serverUri = "ssl://a34bt8gk372w9w-ats.iot.us-east-2.amazonaws.com:8883"
        private val clientId = UUID.randomUUID()
    }

    private val mqttClient: MqttClient

    init {
        // Cargar el certificado de cliente desde el archivo
        val certificateFactory = CertificateFactory.getInstance("X.509")
        val certificateInputStream: InputStream = context.assets.open("cert.der")
        val certificate =
            certificateFactory.generateCertificate(certificateInputStream) as X509Certificate

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
        val keyManagerFactory =
            KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm())
        keyManagerFactory.init(keyStore, null)
        sslContext.init(keyManagerFactory.keyManagers, null, null)

        // Configurar las opciones de conexión MQTT
        val options = MqttConnectOptions()
        options.socketFactory = sslContext.socketFactory
        mqttClient = MqttClient(serverUri, clientId.toString(), MemoryPersistence())
        mqttClient.setCallback(object : MqttCallbackExtended {
            override fun connectionLost(cause: Throwable?) {
                CoroutineScope(Dispatchers.IO).launch {
                    reconnect(options)
                }
            }

            override fun deliveryComplete(token: IMqttDeliveryToken?) {}
            override fun messageArrived(topic: String?, message: MqttMessage?) {}
            override fun connectComplete(reconnect: Boolean, serverURI: String?) {}
        })
        CoroutineScope(Dispatchers.Main).launch {
            reconnect(options)
        }
    }

    private suspend fun reconnect(options: MqttConnectOptions) {
        try {
            println("Intentando reconexión...")
            mqttClient.connect(options)
            if (mqttClient.isConnected) {
                println("Reconexión exitosa")
                // Realizar acciones adicionales después de la reconexión exitosa
            }
        } catch (e: MqttException) {
            println("Error en la reconexión: ${e.message}")
            delay(5000) // Esperar 5 segundos antes de intentar nuevamente
            reconnect(options) // Llamada recursiva para intentar nuevamente la reconexión
        }
    }

    override fun provideMqttClient(): MqttClient {
        return mqttClient
    }
}