package com.nimeshpatel.weatherforcast.di

import android.content.Context
import androidx.room.Room
import com.nimeshpatel.weatherforcast.data.WeatherDao
import com.nimeshpatel.weatherforcast.data.WeatherDatabase
import com.nimeshpatel.weatherforcast.network.WeatherApi
import com.nimeshpatel.weatherforcast.util.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by Nimesh Patel on 28-Sep-24.
 * Purpose:
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Singleton
    @Provides
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao =
        weatherDatabase.weatherDao()

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_database")
            .fallbackToDestructiveMigration().build()


    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherApi =
        Retrofit.Builder().baseUrl(Constant.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(WeatherApi::class.java)

}