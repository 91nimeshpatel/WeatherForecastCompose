package com.nimeshpatel.weatherforcast.screens.settings

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nimeshpatel.weatherforcast.widgets.WeatherAppBar

/**
 * Created by Nimesh Patel on 14-Oct-24.
 * Purpose:
 */
@Composable
fun SettingScreen(navController: NavController, settingViewModel: SettingViewModel = hiltViewModel()) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                navController = navController,
                title = "Settings",
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                isMainScreen = false,
                elevation = 5.dp
            ) {
                navController.popBackStack()
            }
        }

    ) { paddingValues ->
        SettingBody(paddingValues,settingViewModel)
    }
}

@Composable
fun SettingBody(paddingValues: PaddingValues, settingViewModel: SettingViewModel) {
    val isImperial = settingViewModel.isImperial.collectAsState().value
    val degreeSymbol = "\u00B0"
    Surface(modifier = Modifier
        .padding(paddingValues)
        .fillMaxWidth()
        .fillMaxHeight()) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Change Units of Measurement",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.padding(bottom = 15.dp)
            )
            IconToggleButton(checked = isImperial,
                onCheckedChange = {
                    settingViewModel.updateUnites(it)
                },
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .clip(shape = RectangleShape)
                    .padding(5.dp)
                    .background(Color.Magenta.copy(alpha = 0.4f))
                ) {
                Text(text = if(isImperial) "Fahrenheit "+degreeSymbol+"F" else "Celsius "+degreeSymbol+"C")
            }

        }

    }

}
