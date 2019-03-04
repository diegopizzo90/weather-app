package com.example.weatherapp.business.creator

import com.example.weatherapp.business.creator.WeatherDataViewModelCreator.Companion.ERROR_DIRECTION
import com.example.weatherapp.business.creator.WeatherDataViewModelCreator.Companion.NORTH
import com.example.weatherapp.business.creator.WeatherDataViewModelCreator.Companion.SOUTH_EAST
import com.example.weatherapp.business.creator.WeatherDataViewModelCreator.Companion.WEST
import com.example.weatherapp.business.creator.WeatherDataViewModelCreator.Companion.formatDateTimeToString
import com.example.weatherapp.business.datamodel.Main
import com.example.weatherapp.business.datamodel.Weather
import com.example.weatherapp.business.datamodel.WeatherMain
import com.example.weatherapp.business.datamodel.Wind
import com.example.weatherapp.business.dataviewmodel.WeatherDataViewModel
import com.example.weatherapp.business.db.entity.WeatherEntity
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import org.threeten.bp.ZonedDateTime

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class WeatherDataViewModelCreatorTest {

    private val creator = WeatherDataViewModelCreator()

    private fun dummyDataModelFromNetwork(degrees: Double): WeatherMain {
        val weather = Weather("Sunny", "icon.png")
        val main = Main(12.8)
        val wind = Wind(24.6, degrees)
        return WeatherMain(listOf(weather), main, wind)
    }

    private fun dummyDataModelFromDB(degrees: String, dateTime: ZonedDateTime): WeatherEntity {
        return WeatherEntity(1, "Sunny", "icon.png", "12.8", "24.6", degrees, dateTime)
    }

    private fun dummyDataViewModel(degrees: String, isExpired: Boolean, dateTime: String?): WeatherDataViewModel {
        return WeatherDataViewModel(
            "Sunny", "icon.png", "12.8", "24.6", degrees, isExpired, dateTime
        )
    }

    @Test
    fun createDataViewModelFromNetworkTest() {
        val dummyDataModel = dummyDataModelFromNetwork(123.8)
        val dataViewModel = creator.createDataViewModel(dummyDataModel)
        val dummyDataViewModel = dummyDataViewModel(
            SOUTH_EAST, false, formatDateTimeToString(ZonedDateTime.now())!!
        )
        assertEquals(dataViewModel, dummyDataViewModel)
    }

    @Test
    fun checkIfReturnTheCorrectWindSpeedDirection() {
        val degreesValid = 252.9
        val degreesNotValid = 15656.0

        val dummyDataModelWithValidDegrees = dummyDataModelFromNetwork(degreesValid)
        val dataViewModelValid = creator.createDataViewModel(dummyDataModelWithValidDegrees)
        val dummyDataViewModelWithValidDegrees =
            dummyDataViewModel(WEST, false, formatDateTimeToString(ZonedDateTime.now()))
        assertEquals(dataViewModelValid, dummyDataViewModelWithValidDegrees)

        val dummyDataModelWithNotValidDegrees = dummyDataModelFromNetwork(degreesNotValid)
        val dataViewModelNotValid = creator.createDataViewModel(dummyDataModelWithNotValidDegrees)
        val dummyDataViewModelWithNotValidDegrees =
            dummyDataViewModel(ERROR_DIRECTION, false, formatDateTimeToString(ZonedDateTime.now()))
        assertEquals(dataViewModelNotValid, dummyDataViewModelWithNotValidDegrees)
    }

    @Test
    fun createDataViewModelFromDBTest() {
        val dummyDataModel = dummyDataModelFromDB(NORTH, ZonedDateTime.parse("2019-03-03T10:39:37.640Z"))
        val dataViewModel = creator.createDataViewModel(dummyDataModel)
        val dummyDataViewModel =
            dummyDataViewModel(NORTH, true, formatDateTimeToString(ZonedDateTime.parse("2019-03-03T10:39:37.640Z"))!!)
        assertEquals(dataViewModel, dummyDataViewModel)
    }
}