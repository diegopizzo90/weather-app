package com.example.weatherapp.business.cache

import com.example.weatherapp.business.datamodel.WeatherMain
import com.example.weatherapp.business.network.Service
import com.nytimes.android.external.store3.base.impl.Store
import com.nytimes.android.external.store3.base.impl.StoreBuilder
import io.reactivex.Single

class Store(private val service: Service) {

    companion object {
        const val DELIMITER = ";"
    }

    private val weatherStore: Store<WeatherMain, String>

    init {
        weatherStore = StoreBuilder.key<String, WeatherMain>()
            .fetcher { key ->
                service.getWeather(
                    key.split(DELIMITER)[0].toDouble(),
                    key.split(DELIMITER)[1].toDouble()
                )
            }.open()
    }

    fun getWeatherStore(key: String): Single<WeatherMain> {
        return weatherStore.get(key)
    }
}