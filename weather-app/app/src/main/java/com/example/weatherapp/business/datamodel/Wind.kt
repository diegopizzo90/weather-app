package com.example.weatherapp.business.datamodel

import com.google.gson.annotations.Expose

data class Wind(@Expose val speed: Int, @Expose val deg: Int)