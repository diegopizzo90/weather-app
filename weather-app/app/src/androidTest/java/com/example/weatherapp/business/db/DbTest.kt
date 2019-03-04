package com.example.weatherapp.business.db

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.weatherapp.business.db.config.Database
import com.example.weatherapp.business.db.dao.WeatherDao
import com.example.weatherapp.business.db.entity.WeatherEntity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.threeten.bp.ZoneId
import org.threeten.bp.ZonedDateTime
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DbTest {

    private lateinit var weatherDao: WeatherDao
    private lateinit var database: Database

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, Database::class.java).build()
        weatherDao = database.weatherDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun saveWeatherDataAndLoadWeatherDataTest() {
        val weatherEntity =
            WeatherEntity(
                1, "Sunny", "icon", "12", "12", "11",
                ZonedDateTime.parse("2019-03-04T10:39:37.640Z")
            )
        weatherDao.insertWeather(weatherEntity)
        weatherDao.loadWeather().test().assertValue { entity: WeatherEntity ->
            entity.currentCondition.contentEquals("Sunny")
            entity.icon.contentEquals("icon")
            entity.temperature.contentEquals("12")
            entity.windSpeed.contentEquals("12")
            entity.windDirection.contentEquals("12")
            entity.timestamp!!.isEqual(ZonedDateTime.parse("2019-03-04T10:39:37.640Z"))
        }

        weatherDao.loadWeather().test().assertValue { entity: WeatherEntity ->
            entity.timestamp!!.zone == ZoneId.of("UTC")
        }
    }
}