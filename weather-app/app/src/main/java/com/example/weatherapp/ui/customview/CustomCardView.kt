package com.example.weatherapp.ui.customview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.cardview.widget.CardView
import com.example.weatherapp.R
import kotlinx.android.synthetic.main.custom_card.view.*

class CustomCardView(context: Context, attrs: AttributeSet?) : CardView(context, attrs) {

    init {
        View.inflate(context, R.layout.custom_card, this)
    }

    fun setCurrentCondition(currentCondition: String) {
        current_condition_value.text = currentCondition
    }

    fun setTemperature(temperature: Int) {
        temperature_value.text = resources.getString(R.string.temperature_celsius, temperature.toString())
    }

    fun setWindSpeed(windSpeed: Int) {
        wind_speed_value.text = resources.getString(R.string.wind_speed_meter_per_second, windSpeed.toString())
    }

    fun setWindDirection(windDirection: String) {
        wind_direction_value.text = windDirection
    }
}