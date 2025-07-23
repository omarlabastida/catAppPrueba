package com.mx.example.catapp.di

import com.mx.example.catapp.presentation.viewModel.CatViewModel
import org.koin.dsl.module

val ViewModelsModule = module {
    factory { CatViewModel(get(),get()) }
}