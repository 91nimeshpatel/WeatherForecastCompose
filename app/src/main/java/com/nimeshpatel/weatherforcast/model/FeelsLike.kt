package com.nimeshpatel.weatherforcast.model


import kotlinx.parcelize.Parcelize
import android.os.Parcelable

@Parcelize
data class FeelsLike(
    val day: Double,
    val eve: Double,
    val morn: Double,
    val night: Double
) : Parcelable