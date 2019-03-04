package com.example.weatherapp.ui.weatherfragment

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weatherapp.business.dataviewmodel.WeatherDataViewModel
import com.example.weatherapp.business.interactor.Interactor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class WeatherViewModel(
    private val interactor: Interactor
) : ViewModel() {

    private lateinit var disposable: Disposable
    val weatherMutableLiveData: MutableLiveData<WeatherDataViewModel> = MutableLiveData()
    val progressBarVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorMessage: MutableLiveData<String> = MutableLiveData()

    fun getWeatherData(latitude: Double, longitude: Double) {
        val single = interactor.getWeather(latitude, longitude)
        disposable = single.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { onStart() }
            .doFinally { onEnd() }
            .subscribe(
                { t1 -> onSuccess(t1) },
                { onError() })
    }

    private fun onStart() {
        progressBarVisibility.value = View.VISIBLE
        errorMessage.value = null
    }

    private fun onEnd() {
        progressBarVisibility.value = View.GONE
    }

    private fun onSuccess(weatherDataViewModel: WeatherDataViewModel) {
        weatherMutableLiveData.value = weatherDataViewModel
    }

    private fun onError() {
        errorMessage.value = "Sorry, we can't retrieve the data."
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}