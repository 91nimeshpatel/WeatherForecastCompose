package com.nimeshpatel.weatherforcast.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nimeshpatel.weatherforcast.model.Favourite

/**
 * Created by Nimesh Patel on 31-Oct-24.
 * Purpose:
 */
@Database(entities = [Favourite::class], version = 1, exportSchema = true)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}