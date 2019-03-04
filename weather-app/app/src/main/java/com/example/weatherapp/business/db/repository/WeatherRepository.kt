package com.example.weatherapp.business.db.repository

import com.example.weatherapp.business.dataviewmodel.WeatherDataViewModel
import io.reactivex.Single

interface WeatherRepository {

    fun insertWeather(weatherDataViewModel: WeatherDataViewModel)
    fun loadWeather(): Single<WeatherDataViewModel>
}