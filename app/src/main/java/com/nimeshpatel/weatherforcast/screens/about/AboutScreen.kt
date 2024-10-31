package com.nimeshpatel.weatherforcast.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nimeshpatel.weatherforcast.R
import com.nimeshpatel.weatherforcast.widgets.WeatherAppBar

/**
 * Created by Nimesh Patel on 14-Oct-24.
 * Purpose:
 */
@Composable
fun AboutScreen(navController: NavController) {

    Scaffold(
        topBar = {
            WeatherAppBar(
                navController = navController,
                title = "About",
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                isMainScreen = false,
                elevation = 5.dp
            ) {
                navController.popBackStack()
            }
        }

    ) { paddingValues ->
        AboutBody(paddingValues)
    }

}

@Composable
private fun AboutBody(paddingValues: PaddingValues) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(paddingValues),
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(R.string.about_app),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = stringResource(R.string.about_app_desc),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Light
            )
        }

    }
}


@Preview
@Composable
fun AboutPreview() {
    val navController = rememberNavController()
    AboutScreen(navController)
}