package com.mx.example.catapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.mx.example.catapp.di.DataBaseModule
import com.mx.example.catapp.di.NetworkModule
import com.mx.example.catapp.di.PlataformModule
import com.mx.example.catapp.di.RepositoryModule
import com.mx.example.catapp.di.UsesCaseModule
import com.mx.example.catapp.di.ViewModelsModule
import com.mx.example.catapp.presentation.composables.MainContent
import com.mx.example.catapp.presentation.composables.ToastHost
import com.mx.example.catapp.presentation.viewModel.CatViewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.java.KoinJavaComponent.inject

fun main() = application {
    // Inicializa Koin
    startKoin {
        modules(
            NetworkModule,
            RepositoryModule,
            ViewModelsModule,
            UsesCaseModule,
            DataBaseModule,
            PlataformModule
        )
    }

    Window(
        onCloseRequest = ::exitApplication,
        title = "Cat App"
    ) {
        // Composable principal
        MainContent()
    }
}
