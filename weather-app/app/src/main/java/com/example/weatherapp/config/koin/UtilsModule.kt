package com.example.weatherapp.config.koin

import com.example.weatherapp.business.creator.WeatherDataViewModelCreator
import org.koin.dsl.module.module

val utilsModule = module {

    single { WeatherDataViewModelCreator() }
}