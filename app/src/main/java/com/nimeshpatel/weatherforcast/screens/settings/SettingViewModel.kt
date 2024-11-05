package com.nimeshpatel.weatherforcast.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nimeshpatel.weatherforcast.data.datastore.AppPrefImpl
import com.nimeshpatel.weatherforcast.repository.AppPrefRepository
import com.nimeshpatel.weatherforcast.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Nimesh Patel on 05-Nov-24.
 * Purpose:
 */
@HiltViewModel
class SettingViewModel @Inject constructor(
    private val appPrefImpl: AppPrefImpl
): ViewModel() {

    private val _isImperial = MutableStateFlow(false)
    val isImperial = _isImperial.asStateFlow()


    init {

        viewModelScope.launch {
            appPrefImpl.isImperial().collect{
                _isImperial.value = it?: false
            }
        }
    }

    fun updateUnites(isImperial: Boolean){
        viewModelScope.launch {
            appPrefImpl.updateUnits(isImperial)
        }
    }
}