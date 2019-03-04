package com.example.weatherapp.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.bumptech.glide.Glide
import com.example.weatherapp.R
import com.example.weatherapp.ui.utils.glideOptions
import kotlinx.android.synthetic.main.custom_card.view.*

class CustomCardView(context: Context, attrs: AttributeSet?) : CardView(context, attrs) {

    init {
        View.inflate(context, R.layout.custom_card, this)
    }

    fun setCurrentCondition(currentCondition: String) {
        current_condition_value.text = currentCondition
    }

    fun setTemperature(temperature: String) {
        temperature_value.text = resources.getString(R.string.temperature_celsius, temperature)
    }

    fun setIcon(iconUrl: String) {
        Glide.with(context).load("$BASE_URI_IMAGES$iconUrl.png").apply(glideOptions(context))
            .into(icon_value)
    }

    fun setWindSpeed(windSpeed: String) {
        wind_speed_value.text = resources.getString(R.string.wind_speed_meter_per_second, windSpeed)
    }

    fun setWindDirection(windDirection: String) {
        wind_direction_value.text = windDirection
    }

    fun progressBarVisibility(value: Int) {
        progressBar.visibility = value
    }

    companion object {
        private const val BASE_URI_IMAGES = "https://openweathermap.org/img/w/"
    }
}