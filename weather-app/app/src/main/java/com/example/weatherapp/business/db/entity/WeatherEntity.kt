package com.example.weatherapp.business.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.ZonedDateTime

@Entity(tableName = "weather")
data class WeatherEntity(
    @PrimaryKey var id: Int,
    @ColumnInfo(name = "current_condition") var currentCondition: String,
    @ColumnInfo(name = "icon") var icon: String,
    @ColumnInfo(name = "temperature") var temperature: String,
    @ColumnInfo(name = "wind_speed") var windSpeed: String,
    @ColumnInfo(name = "wind_direction") var windDirection: String,
    @ColumnInfo(name = "timestamp") var timestamp: ZonedDateTime? = null
)
