package com.example.weatherapp.config

import android.app.Application
import com.example.weatherapp.config.koin.networkModule
import com.example.weatherapp.config.koin.utilsModule
import com.example.weatherapp.config.koin.viewModelModule
import org.koin.android.ext.android.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, listOf(networkModule, viewModelModule, utilsModule))
    }
}