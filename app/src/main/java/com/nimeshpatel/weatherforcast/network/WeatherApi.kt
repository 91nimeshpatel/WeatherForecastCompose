package com.nimeshpatel.weatherforcast.network

import com.nimeshpatel.weatherforcast.model.Weather
import com.nimeshpatel.weatherforcast.util.Constant
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

/**
 * Created by Nimesh Patel on 04-Oct-24.
 * Purpose:
 */
@Singleton
interface WeatherApi {
    @GET(value = "data/2.5/forecast/daily")
    suspend fun getWeather(
        @Query("q") query: String,
        @Query("units") units: String = "imperial",
        @Query("appid") appId: String = Constant.API_KEY
    ): Weather
}