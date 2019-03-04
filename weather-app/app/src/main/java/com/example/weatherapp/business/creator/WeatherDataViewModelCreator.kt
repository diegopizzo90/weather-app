package com.example.weatherapp.business.creator

import com.example.weatherapp.business.datamodel.WeatherMain
import com.example.weatherapp.business.dataviewmodel.WeatherDataViewModel
import kotlin.math.roundToInt

class WeatherDataViewModelCreator {

    fun createDataViewModel(weatherMain: WeatherMain): WeatherDataViewModel {
        return WeatherDataViewModel(
            weatherMain.weather[0].main,
            weatherMain.weather[0].icon,
            weatherMain.main.temp.toString(),
            weatherMain.wind.speed.toString(),
            fromWindSpeedDegreesToWindSpeedDirection(weatherMain.wind.deg.roundToInt())
        )
    }

    private fun fromWindSpeedDegreesToWindSpeedDirection(degrees: Int): String {
        return when (degrees) {
            in 0..22, in 338..360 -> "North"
            in 23..67 -> "North-East"
            in 68..112 -> "East"
            in 113..157 -> "South-East"
            in 158..202 -> "South"
            in 203..247 -> "South-West"
            in 248..292 -> "West"
            in 293..337 -> "North-West"
            else -> "Direction not valid"
        }
    }
}