package com.example.weatherapp.config.koin

import androidx.room.Room
import com.example.weatherapp.business.db.config.Database
import com.example.weatherapp.business.db.repository.WeatherRepository
import com.example.weatherapp.business.db.repository.impl.WeatherRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

val databaseModule = module {

    //DB
    single {
        Room.databaseBuilder(androidApplication(), Database::class.java, "weather-app").allowMainThreadQueries().build()
    }

    //Dao
    single { get<Database>().weatherDao() }

    //Repository
    single { WeatherRepositoryImpl(get(), get()) as WeatherRepository }
}