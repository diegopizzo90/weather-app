package com.example.weatherapp.config.koin

import com.example.weatherapp.ui.weatherfragment.WeatherViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {
    viewModel { WeatherViewModel(get(), get()) }
}