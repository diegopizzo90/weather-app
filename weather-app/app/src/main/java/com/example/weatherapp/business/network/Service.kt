package com.example.weatherapp.business.network

import com.example.weatherapp.business.datamodel.WeatherMain
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Service {

    companion object {
        const val ENDPOINT = "https://api.openweathermap.org"
        private const val API_KEY_VALUE = "fd36a65cf4eacb750e22e020459481cc"
        private const val API_KEY_NAME = "APPID"
        private const val UNITS = "units"
        private const val UNITS_VALUE = "metric"
    }

    @Headers("Content-Type: application/json")
    @GET("/data/2.5/weather?$API_KEY_NAME=$API_KEY_VALUE&$UNITS=$UNITS_VALUE")
    fun getWeather(@Query("lat") latitude: Double, @Query("lon") longitude: Double): Single<WeatherMain>
}