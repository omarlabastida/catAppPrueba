package com.mx.example.catapp
import android.app.Application
import com.mx.example.catapp.di.DataBaseModule
import com.mx.example.catapp.di.NetworkModule
import com.mx.example.catapp.di.PlataformModule
import com.mx.example.catapp.di.RepositoryModule
import com.mx.example.catapp.di.UsesCaseModule
import com.mx.example.catapp.di.ViewModelsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@Application)
            modules(
                NetworkModule,
                RepositoryModule,
                ViewModelsModule,
                UsesCaseModule,
                DataBaseModule,
                PlataformModule(this@Application)
            )
        }
    }
}