package com.example.weatherapp.ui.weatherfragment

import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.business.dataviewmodel.WeatherDataViewModel
import com.example.weatherapp.business.db.repository.WeatherRepository
import com.example.weatherapp.business.interactor.Interactor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel(
    private val interactor: Interactor,
    private val repository: WeatherRepository,
    private val connectivityManager: ConnectivityManager
) : ViewModel() {

    private lateinit var disposableDB: Disposable
    private lateinit var disposableNetwork: Disposable
    val weatherMutableLiveData: MutableLiveData<WeatherDataViewModel> = MutableLiveData()
    val progressBarVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getWeatherData(latitude: Double, longitude: Double) {
        if (checkIfTheUserIsOnline()) {
            getWeatherDataFromNetwork(latitude, longitude)
        } else {
            getWeatherDataFromDB()
        }
    }

    private fun getWeatherDataFromNetwork(latitude: Double, longitude: Double) {
        val single = interactor.getWeather(latitude, longitude)
        disposableNetwork = single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doFinally { onEnd() }
            .subscribe(
                { weatherData -> onSuccessNetwork(weatherData) },
                { onError() })
    }

    private fun getWeatherDataFromDB() {
        val single = repository.loadWeather()
        disposableDB = single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doFinally { onEnd() }
            .subscribe(
                { weatherData -> onSuccessDB(weatherData) },
                { onError() })
    }

    private fun saveWeatherOnDB(weatherDataViewModel: WeatherDataViewModel) {
        repository.insertWeather(weatherDataViewModel)
    }

    private fun checkIfTheUserIsOnline(): Boolean {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork?.isConnected == true
    }

    private fun onStart() {
        progressBarVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onEnd() {
        progressBarVisibility.value = View.GONE
    }

    private fun onSuccessNetwork(weatherDataViewModel: WeatherDataViewModel) {
        weatherMutableLiveData.value = weatherDataViewModel
        saveWeatherOnDB(weatherDataViewModel)
    }

    private fun onSuccessDB(weatherDataViewModel: WeatherDataViewModel) {
        if (weatherDataViewModel.isExpired == false) weatherMutableLiveData.value = weatherDataViewModel else onError()
    }

    private fun onError() {
        errorMessage.value = "Sorry, we can't retrieve the data."
    }

    override fun onCleared() {
        super.onCleared()
        disposableNetwork.dispose()
        disposableDB.dispose()
    }
}