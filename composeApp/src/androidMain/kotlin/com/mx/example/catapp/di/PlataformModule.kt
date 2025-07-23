package com.mx.example.catapp.di

import android.content.Context
import com.mx.example.catapp.data.local.database.DataBaseDriverFactory
import com.mx.example.catapp.presentation.composables.CatBreedItem
import com.mx.example.catapp.presentation.viewModel.CatViewModel
import com.mx.example.catapp.utils.AndroidToast
import com.mx.example.catapp.utils.MesagesToast
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

fun PlataformModule(context: Context) = module {
    single { DataBaseDriverFactory(context) }
    single<MesagesToast> { AndroidToast(androidContext()) }
}