package com.example.weatherapp.business.datamodel

import com.google.gson.annotations.Expose

data class WeatherMain(@Expose val weather: List<Weather>, @Expose val main: Main, @Expose val wind: Wind)