package com.nimeshpatel.weatherforcast.model


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Coord(
    val lat: Double,
    val lon: Double
) : Parcelable