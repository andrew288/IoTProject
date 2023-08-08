package com.ghostsoftware.iotproject.uis.main.screen.screen_home

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ghostsoftware.iotproject.data.network.ClientMQTT
import com.google.gson.Gson
import com.google.gson.JsonObject
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val clientMQTT: ClientMQTT
) : ViewModel() {

    private val _colorCard = MutableLiveData<Color>()
    val colorCard: LiveData<Color> = _colorCard

    private val _stateLed = MutableLiveData<Boolean>()
    val stateLed: LiveData<Boolean> = _stateLed

    private val _showColorPicker = MutableLiveData<Boolean>()
    val showColorPicker: LiveData<Boolean> = _showColorPicker

    fun publishMessage(message: String) {
        if (clientMQTT.isConnected()) {
            clientMQTT.publish("state/led", message)
        }
    }

    fun onOffLed() {
        var stringMessage = ""

        if (stateLed.value == true) {
            stringMessage = "{\"mode_led\":1, \"on_off\": 1}"
            publishMessage(stringMessage)
        } else {
            stringMessage = "{\"mode_led\":1, \"on_off\": 0}"
            publishMessage(stringMessage)
        }
    }

    fun checkedChange() {
        // obtenemos el valor actual de stateLed
        var currentStateLed = stateLed.value ?: false
        _stateLed.value = !currentStateLed
        currentStateLed = !currentStateLed
        if (!currentStateLed) {
            _showColorPicker.value = false
            _colorCard.value = Color.White
        }
        onOffLed()
    }

    fun clickArrowDropDown() {
        var currentShowColorPicker = showColorPicker.value ?: false
        if (stateLed.value == true) {
            _showColorPicker.value = !currentShowColorPicker
        } else {
            _showColorPicker.value = false
        }
    }

    fun sendValor(hexCode: String) {
        val rgbValues = hexToRgb(hexCode)
        if (rgbValues != null) {
            val (red, green, blue) = rgbValues
            val jsonObject = JsonObject()
            jsonObject.addProperty("on_off", 1)
            jsonObject.addProperty("mode_led", 2)
            jsonObject.addProperty("r", red)
            jsonObject.addProperty("g", green)
            jsonObject.addProperty("b", blue)
            _colorCard.value = Color(red = red / 255f, green = green / 255f, blue = blue / 255f)
            publishMessage(jsonObject.toString())
        }
    }

    fun hexToRgb(hex: String): Triple<Int, Int, Int>? {
        try {
            // Eliminar los dos primeros caracteres para obtener los componentes R, G y B
            val cleanHex = hex.substring(2)

            // Verificar si el código hexadecimal tiene el formato correcto (6 caracteres)
            if (cleanHex.length != 6) {
                return null
            }

            // Obtener los componentes R, G y B como valores enteros en hexadecimal
            val redHex = cleanHex.substring(0, 2)
            val greenHex = cleanHex.substring(2, 4)
            val blueHex = cleanHex.substring(4)

            // Convertir los valores hexadecimales a decimales
            val red = redHex.toInt(16)
            val green = greenHex.toInt(16)
            val blue = blueHex.toInt(16)

            // Retornar los valores RGB en una Triple (Triple<Int, Int, Int>)
            return Triple(red, green, blue)
        } catch (e: NumberFormatException) {
            // Si ocurre un error de formato, retorna null
            return null
        }
    }
}