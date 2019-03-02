package com.example.weatherapp.ui.weatherfragment

import androidx.lifecycle.ViewModel
import com.example.weatherapp.business.interactor.Interactor

class WeatherViewModel(private val interactor: Interactor) : ViewModel() {

    fun getWeatherData(latitude: Double, longitude: Double) {

    }
}