package com.nimeshpatel.weatherforcast.model


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class WeatherObject(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
) : Parcelable