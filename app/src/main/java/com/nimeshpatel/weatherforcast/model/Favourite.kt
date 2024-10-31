package com.nimeshpatel.weatherforcast.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Nimesh Patel on 31-Oct-24.
 * Purpose:
 */
@Entity(tableName = "fav_tbl")
data class Favourite(

    @PrimaryKey
    @ColumnInfo(name = "city")
    val city: String,


    @ColumnInfo(name = "country")
    val country: String

)