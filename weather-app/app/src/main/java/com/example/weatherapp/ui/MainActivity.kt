package com.example.weatherapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.R
import com.example.weatherapp.ui.weatherfragment.WeatherFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag(WeatherFragment.TAG_WEATHER_FRAGMENT)
        if (fragment == null) {
            supportFragmentManager.beginTransaction().replace(
                R.id.fragment_container,
                WeatherFragment.newInstance(null),
                WeatherFragment.TAG_WEATHER_FRAGMENT
            ).commit()
        }
    }
}
