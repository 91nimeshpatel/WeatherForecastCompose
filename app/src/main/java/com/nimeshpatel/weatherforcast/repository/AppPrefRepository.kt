package com.nimeshpatel.weatherforcast.repository

import kotlinx.coroutines.flow.Flow

/**
 * Created by Nimesh Patel on 05-Nov-24.
 * Purpose:
 */
interface AppPrefRepository {

    fun isImperial() : Flow<Boolean?>

    suspend fun updateUnits(isImperial : Boolean)
}