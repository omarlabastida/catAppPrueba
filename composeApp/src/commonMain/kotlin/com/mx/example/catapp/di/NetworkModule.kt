package com.mx.example.catapp.di

import com.mx.example.catapp.data.remote.ApiService
import com.mx.example.catapp.data.remote.KtorClientProvider
import org.koin.dsl.module

val NetworkModule = module{

    single { KtorClientProvider.httpClient}// Providing the HttpClient instance from KtorClientProvider
    single { ApiService(get()) } // Injecting the HttpClient into ApiService
}