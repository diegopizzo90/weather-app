package com.example.weatherapp.business.interactor

import com.example.weatherapp.business.cache.Store
import com.example.weatherapp.business.cache.Store.Companion.DELIMITER
import com.example.weatherapp.business.creator.WeatherDataViewModelCreator
import com.example.weatherapp.business.datamodel.WeatherMain
import com.example.weatherapp.business.dataviewmodel.WeatherDataViewModel
import io.reactivex.Single

class Interactor(private val store: Store, private val creator: WeatherDataViewModelCreator) {

    fun getWeather(latitude: Double, longitude: Double): Single<WeatherDataViewModel> {
        return store.getWeatherStore("$latitude$DELIMITER$longitude")
            .map { t: WeatherMain -> creator.createDataViewModel(t) }
    }
}