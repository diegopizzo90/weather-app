package com.example.weatherapp.business.interactor

import com.example.weatherapp.business.creator.WeatherDataViewModelCreator
import com.example.weatherapp.business.datamodel.WeatherMain
import com.example.weatherapp.business.dataviewmodel.WeatherDataViewModel
import com.example.weatherapp.business.network.Service
import io.reactivex.Single

class Interactor(private val service: Service, private val creator: WeatherDataViewModelCreator) {

    fun getWeather(latitude: Double, longitude: Double): Single<WeatherDataViewModel> {
        return service.getWeather(latitude, longitude)
            .map { t: WeatherMain -> creator.createDataViewModel(t) }
    }
}