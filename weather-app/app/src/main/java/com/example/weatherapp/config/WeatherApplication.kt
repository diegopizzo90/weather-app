package com.example.weatherapp.config

import android.app.Application
import com.example.weatherapp.config.koin.databaseModule
import com.example.weatherapp.config.koin.networkModule
import com.example.weatherapp.config.koin.utilsModule
import com.example.weatherapp.config.koin.viewModelModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.android.startKoin

class WeatherApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin(this, listOf(networkModule, viewModelModule, utilsModule, databaseModule))
    }
}