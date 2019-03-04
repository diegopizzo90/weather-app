package com.example.weatherapp.business.interactor

import com.example.weatherapp.business.cache.Store
import com.example.weatherapp.business.cache.Store.Companion.DELIMITER
import com.example.weatherapp.business.datamodel.WeatherMain
import io.reactivex.Single

class Interactor(private val store: Store) {

    fun getWeather(latitude: Double, longitude: Double): Single<WeatherMain> {
        return store.getWeatherStore("$latitude$DELIMITER$longitude")
    }
}