package com.nimeshpatel.weatherforcast.model


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class Temp(
    val day: Double,
    val eve: Double,
    val max: Double,
    val min: Double,
    val morn: Double,
    val night: Double
) : Parcelable