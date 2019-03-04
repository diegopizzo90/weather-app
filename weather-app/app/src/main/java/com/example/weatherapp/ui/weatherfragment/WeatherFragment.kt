package com.example.weatherapp.ui.weatherfragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.weatherapp.R
import com.example.weatherapp.business.dataviewmodel.WeatherDataViewModel
import com.example.weatherapp.databinding.WeatherFragmentLayoutBinding
import com.google.android.gms.location.LocationRequest
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import org.koin.android.viewmodel.ext.android.viewModel
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider
import java.util.concurrent.TimeUnit


class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModel()
    private lateinit var binding: WeatherFragmentLayoutBinding
    private lateinit var disposable: Disposable

    companion object {
        const val TAG_WEATHER_FRAGMENT = "weatherFragmentTag"
        const val LOCATION_TIMEOUT_IN_SECONDS: Long = 5000


        fun newInstance(bundle: Bundle?): WeatherFragment {
            val weatherFragment = WeatherFragment()
            if (bundle != null) {
                weatherFragment.arguments = bundle
            }
            return weatherFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.weather_fragment_layout, container, false)
        binding.viewModel = viewModel

        getLocationAndGetWeatherData(false)
        setFab()

        viewModel.weatherMutableLiveData.observe(this, Observer { weatherMain -> setWeatherCardData(weatherMain) })

        viewModel.progressBarVisibility.observe(
            this, Observer { value -> binding.weatherCard.progressBarVisibility(value) })

        return binding.root
    }

    private fun showMessage(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG).show()
    }

    private fun callApi(latitude: Double, longitude: Double, isFromFab: Boolean) {
        viewModel.getWeatherData(latitude, longitude, isFromFab)
    }

    private fun setFab() {
        binding.refreshFloatingActionButton.setOnClickListener {
            getLocationAndGetWeatherData(true)
            viewModel.errorMessage.observe(this, Observer { t -> if (t != null) showMessage(t) })
        }
    }

    private fun setWeatherCardData(weatherDataVM: WeatherDataViewModel) {
        binding.weatherCard.setCurrentCondition(weatherDataVM.currentCondition)
        binding.weatherCard.setIcon(weatherDataVM.icon)
        binding.weatherCard.setTemperature(weatherDataVM.temperature)
        binding.weatherCard.setWindSpeed(weatherDataVM.windSpeed)
        binding.weatherCard.setWindDirection(weatherDataVM.windDirection)
        binding.lastUpdateTextView.text = weatherDataVM.lastUpdate
    }

    @SuppressLint("MissingPermission")
    private fun getLocationAndGetWeatherData(isFromFab: Boolean) {
        val locationProvider = ReactiveLocationProvider(context)

        val req = LocationRequest.create()
            .setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY)
            .setExpirationDuration(TimeUnit.SECONDS.toMillis(LOCATION_TIMEOUT_IN_SECONDS))

        val locationObservable = locationProvider.getUpdatedLocation(req)
            .observeOn(AndroidSchedulers.mainThread()).firstElement()

        disposable =
            locationObservable.subscribe { location -> callApi(location.latitude, location.longitude, isFromFab) }
    }

    override fun onStop() {
        super.onStop()
        disposable.dispose()
    }
}