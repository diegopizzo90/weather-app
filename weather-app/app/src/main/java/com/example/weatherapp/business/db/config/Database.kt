package com.example.weatherapp.business.db.config

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.weatherapp.business.db.converter.TimestampConverter
import com.example.weatherapp.business.db.dao.WeatherDao
import com.example.weatherapp.business.db.entity.WeatherEntity

@Database(entities = [WeatherEntity::class], version = 1)
@TypeConverters(TimestampConverter::class)
abstract class Database : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}