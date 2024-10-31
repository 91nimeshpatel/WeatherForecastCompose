package com.nimeshpatel.weatherforcast.screens.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nimeshpatel.weatherforcast.model.Favourite
import com.nimeshpatel.weatherforcast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Nimesh Patel on 01-Nov-24.
 * Purpose:
 */
@HiltViewModel
class FavouriteViewModel @Inject constructor(private val repository: WeatherDbRepository) :
    ViewModel() {

    private var _favouriteHistoryList = MutableStateFlow<List<Favourite>>(emptyList())
    val favouriteHistoryList = _favouriteHistoryList.asStateFlow()


    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavourites().distinctUntilChanged().collect { mList ->
                    _favouriteHistoryList.value = mList
            }
        }

    }

    fun insertFavourite(favourite: Favourite) = viewModelScope.launch { repository.insertFavourite(favourite = favourite) }
    fun updateFavourite(favourite: Favourite) = viewModelScope.launch { repository.updateFavorite(favourite = favourite) }
    fun deleteFavourite(favourite: Favourite) = viewModelScope.launch { repository.deleteFavourite(favourite = favourite) }

}