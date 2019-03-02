package com.example.weatherapp.business.datamodel

import com.google.gson.annotations.Expose

data class Weather(@Expose val main: String, @Expose val icon: String)