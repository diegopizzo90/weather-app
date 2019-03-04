package com.example.weatherapp.business.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.weatherapp.business.db.entity.WeatherEntity
import io.reactivex.Single

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weatherEntity: WeatherEntity)

    @Query("SELECT * FROM weather where id = '1'")
    fun loadWeather(): Single<WeatherEntity>
}