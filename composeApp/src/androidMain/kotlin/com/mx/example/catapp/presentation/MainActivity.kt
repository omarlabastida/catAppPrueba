package com.mx.example.catapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.mx.example.catapp.App
import com.mx.example.catapp.presentation.viewModel.CatViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: CatViewModel = getViewModel()
            App(viewModel)
        }
    }
}
