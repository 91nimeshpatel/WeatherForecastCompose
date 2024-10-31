package com.nimeshpatel.weatherforcast.screens.settings

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nimeshpatel.weatherforcast.widgets.WeatherAppBar

/**
 * Created by Nimesh Patel on 14-Oct-24.
 * Purpose:
 */
@Composable
fun SettingScreen(navController: NavController) {
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
        SettingBody(paddingValues)
    }
}

@Composable
fun SettingBody(paddingValues: PaddingValues) {
    Surface(modifier = Modifier.padding(paddingValues)) {

        Text("Setting Screen")
    }

}
