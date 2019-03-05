package com.example.weatherapp.business.db.config

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.business.db.converter.ZonedDateTimeConverter
import com.example.weatherapp.business.db.dao.WeatherDao
import com.example.weatherapp.business.db.entity.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1)
@TypeConverters(ZonedDateTimeConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}