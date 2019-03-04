package com.example.weatherapp.business.creator

import com.example.weatherapp.business.datamodel.WeatherMain
import com.example.weatherapp.business.dataviewmodel.WeatherDataViewModel
import com.example.weatherapp.business.db.entity.WeatherEntity
import org.threeten.bp.Duration
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.format.DateTimeFormatter
import kotlin.math.roundToInt

class WeatherDataViewModelCreator {

    fun createDataViewModel(weatherMain: WeatherMain): WeatherDataViewModel {
        return WeatherDataViewModel(
            weatherMain.weather[0].main,
            weatherMain.weather[0].icon,
            weatherMain.main.temp.toString(),
            weatherMain.wind.speed.toString(),
            fromWindSpeedDegreesToWindSpeedDirection(weatherMain.wind.deg.roundToInt()),
            false,
            formatDateTimeToString(ZonedDateTime.now())
        )
    }

    fun createDataViewModel(weatherEntity: WeatherEntity): WeatherDataViewModel {
        return WeatherDataViewModel(
            weatherEntity.currentCondition,
            weatherEntity.icon,
            weatherEntity.temperature,
            weatherEntity.windSpeed,
            weatherEntity.windDirection,
            checkIfDataIsExpired(weatherEntity.timestamp),
            formatDateTimeToString(weatherEntity.timestamp)
        )
    }

    fun createEntity(weatherDataViewModel: WeatherDataViewModel): WeatherEntity {
        return WeatherEntity(
            1, weatherDataViewModel.currentCondition,
            weatherDataViewModel.icon,
            weatherDataViewModel.temperature,
            weatherDataViewModel.windSpeed,
            weatherDataViewModel.windDirection,
            ZonedDateTime.now()
        )
    }

    private fun checkIfDataIsExpired(dateTime: ZonedDateTime?): Boolean {
        if (dateTime == null) return false
        val hours = Duration.between(dateTime, ZonedDateTime.now()).toHours()
        return (hours > 24)
    }

    private fun fromWindSpeedDegreesToWindSpeedDirection(degrees: Int): String {
        return when (degrees) {
            in 0..22, in 338..360 -> NORTH
            in 23..67 -> NORTH_EAST
            in 68..112 -> EAST
            in 113..157 -> SOUTH_EAST
            in 158..202 -> SOUTH
            in 203..247 -> SOUTH_WEST
            in 248..292 -> WEST
            in 293..337 -> NORTH_WEST
            else -> ERROR_DIRECTION
        }
    }

    companion object {
        const val NORTH = "North"
        const val NORTH_EAST = "North-East"
        const val EAST = "East"
        const val SOUTH_EAST = "South-East"
        const val SOUTH = "South"
        const val SOUTH_WEST = "South-West"
        const val WEST = "West"
        const val NORTH_WEST = "North-West"
        const val ERROR_DIRECTION = "Direction not valid"
        private const val DATE_PATTERN = "EEEE, MMM dd, yyyy HH:mm"

        fun formatDateTimeToString(dateTime: ZonedDateTime?): String? {
            DateTimeFormatter.ofPattern(DATE_PATTERN)
            return dateTime?.format(DateTimeFormatter.ofPattern(DATE_PATTERN))
        }
    }
}