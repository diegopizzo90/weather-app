package com.example.weatherapp.business.dataviewmodel

data class WeatherDataViewModel(
    val currentCondition: String,
    val icon: String,
    val temperature: String,
    val windSpeed: String,
    val windDirection: String,
    val isExpired: Boolean?
)