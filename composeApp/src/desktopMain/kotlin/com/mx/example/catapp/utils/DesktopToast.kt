package com.mx.example.catapp.utils

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupPositionProvider
import kotlinx.coroutines.*
import androidx.compose.runtime.State

class DesktopToast: MesagesToast {

    private val _currentMessage = mutableStateOf<String?>(null)
    val currentMessage: State<String?> = _currentMessage

    override fun showToast(message: String) {
        _currentMessage.value = message
        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            _currentMessage.value = null
        }
    }
}