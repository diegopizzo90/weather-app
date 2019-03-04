package com.example.weatherapp.config.koin

import android.content.Context
import android.net.ConnectivityManager
import com.example.weatherapp.business.creator.WeatherDataViewModelCreator
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val utilsModule = module {

    single { WeatherDataViewModelCreator() }
    single { androidApplication().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager }
}