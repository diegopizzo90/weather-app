package com.example.weatherapp.config.koin

import com.example.weatherapp.business.creator.WeatherDataViewModelCreator
import com.example.weatherapp.business.interactor.Interactor
import com.example.weatherapp.business.network.Service
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { providesRetrofit(get()) }
    single { providesGsonConverterFactory(get()) }
    single { providesGson() }
    single { providesService(get()) }
    single { providesServiceInteractor(get(), get()) }

}

fun providesRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
    return Retrofit.Builder()
        .baseUrl(Service.ENDPOINT)
        .addConverterFactory(gsonConverterFactory)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

fun providesGsonConverterFactory(gson: Gson): GsonConverterFactory {
    return GsonConverterFactory.create(gson)
}

fun providesGson(): Gson {
    return GsonBuilder().serializeNulls().create()
}

fun providesService(retrofit: Retrofit): Service {
    return retrofit.create(Service::class.java)
}

fun providesServiceInteractor(service: Service, creator: WeatherDataViewModelCreator): Interactor {
    return Interactor(service, creator)
}