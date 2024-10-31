package com.nimeshpatel.weatherforcast.repository

import com.nimeshpatel.weatherforcast.data.DataOrException
import com.nimeshpatel.weatherforcast.model.Weather
import com.nimeshpatel.weatherforcast.network.WeatherApi
import javax.inject.Inject

/**
 * Created by Nimesh Patel on 07-Oct-24.
 * Purpose:
 */
class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather(cityQuery: String, units: String):
            DataOrException<Weather, Boolean, Exception> {

        val response = try {
            api.getWeather(query = cityQuery, units = units)

        } catch (e: Exception) {
            return DataOrException(e = e)
        }

        return DataOrException(data = response)
    }
}