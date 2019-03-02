package com.example.weatherapp.ui.weatherfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.WeatherFragmentLayoutBinding
import org.koin.android.viewmodel.ext.android.viewModel

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by viewModel()
    private lateinit var binding: WeatherFragmentLayoutBinding

    companion object {
        const val TAG_WEATHER_FRAGMENT = "weatherFragmentTag"

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
        return binding.root
    }
}