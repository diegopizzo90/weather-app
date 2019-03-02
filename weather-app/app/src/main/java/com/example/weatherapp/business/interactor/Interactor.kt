package com.example.weatherapp.business.interactor

import com.example.weatherapp.business.cache.Store
import com.example.weatherapp.business.cache.Store.Companion.DELIMITER

class Interactor(private val store: Store) {

    fun getWeather(latitude: Double, longitude: Double) {
        store.getWeatherStore("$latitude$DELIMITER$longitude")
    }
}