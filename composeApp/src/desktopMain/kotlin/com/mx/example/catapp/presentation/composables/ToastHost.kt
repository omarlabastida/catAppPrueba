package com.mx.example.catapp.presentation.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mx.example.catapp.presentation.theme.CatPrimary
import com.mx.example.catapp.presentation.theme.CatTextPrimary
import com.mx.example.catapp.utils.DesktopToast

@Composable
fun ToastHost(toast: DesktopToast) {
    val message by toast.currentMessage

    if (message != null) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .wrapContentHeight()
                .background(CatTextPrimary, shape = RoundedCornerShape(8.dp))
                .padding(12.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Text(message ?: "", color = CatPrimary)
        }
    }
}
