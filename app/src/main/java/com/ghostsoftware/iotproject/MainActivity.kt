package com.ghostsoftware.iotproject

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.eclipse.paho.client.mqttv3.MqttClient
import org.eclipse.paho.client.mqttv3.MqttConnectOptions
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
import javax.net.ssl.KeyManagerFactory
import javax.net.ssl.SSLContext

class MainActivity : ComponentActivity() {
    private val mqttServerUri = "ssl://a34bt8gk372w9w-ats.iot.us-east-2.amazonaws.com:8883"
    private val clientId = UUID.randomUUID()
    private val topic = "outTopic"
    private val qos = 0

    private lateinit var mqttClient: MqttClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val responseSubcribe = remember {
                mutableStateOf("")
            }
                // A surface container using the 'background' color from the them
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            val message = "0"
                            mqttClient.publish("inTopic", MqttMessage(message.toByteArray()) )
                        }) {
                            Text(text = "Pusblish Off")
                        }
                        Button(onClick = {
                            val message = "1"
                            mqttClient.publish("inTopic", MqttMessage(message.toByteArray()) )
                        }) {
                            Text(text = "Pusblish On")
                        }
                    }

                }

            try{
                // Cargar el certificado de cliente desde el archivo
                val certificateFactory = CertificateFactory.getInstance("X.509")
                val certificateInputStream: InputStream = applicationContext.assets.open("cert.der")
                val certificate = certificateFactory.generateCertificate(certificateInputStream) as X509Certificate

                // Cargar la clave privada desde el archivo
                val keyInputStream: InputStream = applicationContext.assets.open("private.der")
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
                mqttClient = MqttClient(mqttServerUri, clientId.toString(), MemoryPersistence())
                mqttClient.connect(options)
                mqttClient.subscribe(topic, qos) { _, message ->
                    val luminosity = message.payload.toString(Charsets.UTF_8)
                    Log.d("TEST-MQTT",luminosity)
                    responseSubcribe.value = responseSubcribe.value  + "MESSAGE: " + luminosity
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mqttClient.disconnect()
    }
}