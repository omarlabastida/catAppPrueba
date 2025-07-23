package com.mx.example.catapp.presentation.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.mx.example.catapp.App
import com.mx.example.catapp.presentation.viewModel.CatViewModel
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun MainContent() {
    val viewModel = remember { getKoin().get<CatViewModel>() }

    MaterialTheme {
        App(viewModel = viewModel)
    }
}