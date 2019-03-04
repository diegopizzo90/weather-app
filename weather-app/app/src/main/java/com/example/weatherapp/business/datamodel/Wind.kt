package com.example.weatherapp.business.datamodel

import com.google.gson.annotations.Expose

data class Wind(@Expose val speed: Double, @Expose val deg: Double)