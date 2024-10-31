package com.nimeshpatel.weatherforcast.repository

import com.nimeshpatel.weatherforcast.data.WeatherDao
import com.nimeshpatel.weatherforcast.model.Favourite
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by Nimesh Patel on 31-Oct-24.
 * Purpose:
 */
class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavourites(): Flow<List<Favourite>> = weatherDao.getFavourites()
    suspend fun insertFavourite(favourite: Favourite) = weatherDao.insertFavourite(favourite = favourite)
    suspend fun updateFavorite(favourite: Favourite) = weatherDao.updateFavourite(favourite = favourite)
    suspend fun deleteAllFavorites() = weatherDao.deleteAllFavourites()
    suspend fun deleteFavourite(favourite: Favourite) = weatherDao.deleteFavourite(favourite = favourite)
    suspend fun getFavouriteById(city: String) = weatherDao.getFavCityById(city = city)

}