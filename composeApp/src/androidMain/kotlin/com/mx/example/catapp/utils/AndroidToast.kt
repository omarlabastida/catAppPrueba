package com.mx.example.catapp.utils

import android.content.Context
import android.widget.Toast

class AndroidToast(private val context: Context) : MesagesToast {
    override fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
}
