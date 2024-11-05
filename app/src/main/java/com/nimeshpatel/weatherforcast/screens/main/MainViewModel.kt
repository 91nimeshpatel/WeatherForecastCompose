package com.nimeshpatel.weatherforcast.screens.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimeshpatel.weatherforcast.data.DataOrException
import com.nimeshpatel.weatherforcast.data.datastore.AppPrefImpl
import com.nimeshpatel.weatherforcast.model.Weather
import com.nimeshpatel.weatherforcast.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Nimesh Patel on 07-Oct-24.
 * Purpose:
 */
@HiltViewModel
class MainViewModel @Inject constructor(private val repository: WeatherRepository,
    private val appPrefImpl: AppPrefImpl) : ViewModel() {
    private val _isImperial = MutableStateFlow(false)
    val isImperial = _isImperial.asStateFlow()

    init {

        viewModelScope.launch {
            appPrefImpl.isImperial().collect{
                _isImperial.value = it?: false
            }
        }
    }


    suspend fun getWeatherData(cityQuery: String):
            DataOrException<Weather, Boolean, Exception> {

        Log.e("neem", "getWeatherData: ${isImperial.value}", )
        return repository.getWeather(cityQuery = cityQuery, units = if(isImperial.value) "imperial" else "metric")

    }
}