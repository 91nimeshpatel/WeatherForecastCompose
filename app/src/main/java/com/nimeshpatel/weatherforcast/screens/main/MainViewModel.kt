package com.nimeshpatel.weatherforcast.screens.main

import androidx.lifecycle.ViewModel
import com.nimeshpatel.weatherforcast.data.DataOrException
import com.nimeshpatel.weatherforcast.model.Weather
import com.nimeshpatel.weatherforcast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Nimesh Patel on 07-Oct-24.
 * Purpose:
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository) : ViewModel() {

    suspend fun getWeatherData(cityQuery: String, units: String):
            DataOrException<Weather, Boolean, Exception> {

        return repository.getWeather(cityQuery = cityQuery, units =units)

    }
}