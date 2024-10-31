package com.nimeshpatel.weatherforcast.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nimeshpatel.weatherforcast.data.DataOrException
import com.nimeshpatel.weatherforcast.model.Weather
import com.nimeshpatel.weatherforcast.model.WeatherItem
import com.nimeshpatel.weatherforcast.navigation.WeatherScreens
import com.nimeshpatel.weatherforcast.util.formatDate
import com.nimeshpatel.weatherforcast.util.formatDecimals
import com.nimeshpatel.weatherforcast.widgets.HumidityWindPressureRow
import com.nimeshpatel.weatherforcast.widgets.SunsetSunRiseRow
import com.nimeshpatel.weatherforcast.widgets.WeatherAppBar
import com.nimeshpatel.weatherforcast.widgets.WeatherDetailRow
import com.nimeshpatel.weatherforcast.widgets.WeatherStateImage


/**
 * Created by Nimesh Patel on 28-Sep-24.
 * Purpose:
 */
@Composable
fun MainScreen(
    navController: NavController,
    mainViewModel: MainViewModel = hiltViewModel(),
    city: String
) {

    val weatherData = produceState<DataOrException<Weather, Boolean, Exception>>(
        initialValue = DataOrException(loading = true)
    ) {
        value = mainViewModel.getWeatherData(cityQuery = city, "imperial")
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        MainScaffold(weatherData.data, navController)
    }
}


@Composable
fun MainScaffold(
    weather: Weather?,
    navController: NavController
) {

    val cityName = weather?.city?.name ?:""
    val countryName = weather?.city?.country?:""
    val title = if(cityName.isNotEmpty()) "$cityName, $countryName" else countryName

    var isImperial by remember {
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            WeatherAppBar(
                title = title,
                navController = navController,
                onAddActionClicked = {
                    navController.navigate(WeatherScreens.SearchScreen.name)
                },
                elevation = 5.dp
            )
        },
        modifier = Modifier.background(color = MaterialTheme.colorScheme.onBackground)
    ) { paddingValues ->
        MainContent(weather, Modifier.padding(paddingValues), isImperial)
    }

}

@Composable
fun MainContent(weather: Weather?, modifier: Modifier, isImperial: Boolean) {
    val weatherItem = weather?.list?.get(0)
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem?.weather?.get(0)?.icon}.png"
    Column(
        modifier = modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(weatherItem?.dt ?: 0),
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .padding(4.dp)
                .size(200.dp),
            shape = CircleShape,
            color = Color(0xFFFFC400)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                WeatherStateImage(imageUrl)

                Text(
                    text = formatDecimals(weatherItem?.temp?.day ?: 0.0) + "\u00B0",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.ExtraBold
                )

                Text(
                    text = weatherItem?.weather?.get(0)?.main ?: "",
                    fontStyle = FontStyle.Italic
                )

            }
        }
        HumidityWindPressureRow(weatherItem, isImperial)
        HorizontalDivider()
        SunsetSunRiseRow(weatherItem)
        Text(
            text = "This Week",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            color = Color(0xFFEEF1EF),
            shape = RoundedCornerShape(size = 14.dp)
        ) {
            LazyColumn(
                modifier = Modifier.padding(2.dp),
                contentPadding = PaddingValues(1.dp)
            ) {
                items(items = weather?.list ?: emptyList()) { weatherItem: WeatherItem ->
                    WeatherDetailRow(weatherItem = weatherItem)
                }
            }

        }
    }

}
