package com.mx.example.catapp.di

import com.mx.example.catapp.data.local.database.DataBaseDriverFactory
import com.mx.example.catapp.utils.DesktopToast
import com.mx.example.catapp.utils.MesagesToast
import org.koin.dsl.module

val PlataformModule = module{
    single { DataBaseDriverFactory() }
    single<MesagesToast> { DesktopToast() }
}