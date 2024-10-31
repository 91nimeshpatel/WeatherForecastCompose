package com.nimeshpatel.weatherforcast.model


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Weather(
    val city: City,
    val cnt: Int,
    val cod: String,
    val list: List<WeatherItem>,
    val message: Double
) : Parcelable