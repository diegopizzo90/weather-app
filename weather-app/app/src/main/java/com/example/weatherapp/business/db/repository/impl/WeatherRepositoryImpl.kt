package com.example.weatherapp.business.db.repository.impl

import com.example.weatherapp.business.creator.WeatherDataViewModelCreator
import com.example.weatherapp.business.dataviewmodel.WeatherDataViewModel
import com.example.weatherapp.business.db.dao.WeatherDao
import com.example.weatherapp.business.db.entity.WeatherEntity
import com.example.weatherapp.business.db.repository.WeatherRepository
import io.reactivex.Single

class WeatherRepositoryImpl(private val weatherDao: WeatherDao, private val creator: WeatherDataViewModelCreator) :
    WeatherRepository {

    override fun insertWeather(weatherDataViewModel: WeatherDataViewModel) {
        return weatherDao.insertWeather(creator.createEntity(weatherDataViewModel))
    }

    override fun loadWeather(): Single<WeatherDataViewModel> {
        return weatherDao.loadWeather().map { t: WeatherEntity -> creator.createDataViewModel(t) }
    }
}