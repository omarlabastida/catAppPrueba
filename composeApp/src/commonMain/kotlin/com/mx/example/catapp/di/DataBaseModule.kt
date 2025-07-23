package com.mx.example.catapp.di

import com.mx.example.catapp.data.local.database.CatAppDatabase
import com.mx.example.catapp.data.local.database.DataBaseDriverFactory
import org.koin.dsl.module

val DataBaseModule = module {
    single {
        val driverFactory = get<DataBaseDriverFactory>()
        CatAppDatabase(driverFactory.createDriver())
    }
}