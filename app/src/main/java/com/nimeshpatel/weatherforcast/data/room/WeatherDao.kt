package com.nimeshpatel.weatherforcast.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nimeshpatel.weatherforcast.model.Favourite
import kotlinx.coroutines.flow.Flow

/**
 * Created by Nimesh Patel on 31-Oct-24.
 * Purpose:
 */
@Dao
interface WeatherDao {

    @Query("SELECT * From fav_tbl")
    fun getFavourites(): Flow<List<Favourite>>

    @Query("SELECT * from fav_tbl WHERE city =:city")
    fun getFavCityById(city: String) : Favourite

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: Favourite)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateFavourite(favourite: Favourite)

    @Query("DELETE from fav_tbl")
    suspend fun deleteAllFavourites()

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)
}