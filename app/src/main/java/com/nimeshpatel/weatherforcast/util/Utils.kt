package com.nimeshpatel.weatherforcast.util

import android.annotation.SuppressLint
import android.util.Log
import java.text.SimpleDateFormat
import java.util.Date

/**
 * Created by Nimesh Patel on 10-Oct-24.
 * Purpose:
 */

fun formatDate(timestamp: Int): String{
    val sdf = SimpleDateFormat("EEE, MMM d")
    val date = Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

fun formatDateTime(timestamp: Int):String{
    val sdf = SimpleDateFormat("hh:mm:aa")
    val date = Date(timestamp.toLong() * 1000)

    return sdf.format(date)
}

@SuppressLint("DefaultLocale")
fun formatDecimals(items : Double): String {
    return String.format("%.0f", items) // Formatting without decimal places
}