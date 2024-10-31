package com.nimeshpatel.weatherforcast.data

/**
 * Created by Nimesh Patel on 07-Oct-24.
 * Purpose:
 */
class DataOrException<T, Boolean, E : Exception>(
    var data: T? = null,
    var loading: kotlin.Boolean? = null,
    var e: E? = null
)