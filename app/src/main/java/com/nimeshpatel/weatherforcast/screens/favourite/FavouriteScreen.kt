package com.nimeshpatel.weatherforcast.screens.favourite

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.nimeshpatel.weatherforcast.model.Favourite
import com.nimeshpatel.weatherforcast.navigation.WeatherScreens
import com.nimeshpatel.weatherforcast.widgets.WeatherAppBar

/**
 * Created by Nimesh Patel on 14-Oct-24.
 * Purpose:
 */
@Composable
fun FavouriteScreen(
    navController: NavController,
    favouriteViewModel: FavouriteViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = {
            WeatherAppBar(
                navController = navController,
                title = "Favourite",
                icon = Icons.AutoMirrored.Filled.ArrowBack,
                isMainScreen = false,
                elevation = 5.dp
            ) {
                navController.popBackStack()
            }
        }

    ) { paddingValues ->
        FavouriteBody(paddingValues, navController, favouriteViewModel)
    }
}

@Composable
fun FavouriteBody(
    paddingValues: PaddingValues,
    navController: NavController,
    favouriteViewModel: FavouriteViewModel
) {
    Surface(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxWidth()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val list = favouriteViewModel.favouriteHistoryList.collectAsState().value

            LazyColumn {
                items(items = list) { favourite ->
                    CityRow(
                        favourite = favourite,
                        navController = navController,
                        favouriteViewModel = favouriteViewModel
                    )

                }
            }
        }
    }
}

@Composable
fun CityRow(
    favourite: Favourite,
    navController: NavController,
    favouriteViewModel: FavouriteViewModel
) {

    Surface(
        Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(WeatherScreens.MainScreen.name + "/${favourite.city}")
            },
        shape = CircleShape.copy(topEnd = CornerSize(16.dp)),
        color =  Color(0xFFB2DFDB)
    ) {

        Row(modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {

            Text(text = favourite.city,
                modifier = Modifier.padding(start = 4.dp))

            Surface(modifier = Modifier.padding(0.dp),
                shape = CircleShape,
                color = Color(0xFFD1E3E1)
            ) {
                Text(text = favourite.country,
                    modifier = Modifier.padding(4.dp),
                    style = MaterialTheme.typography.displaySmall)
            }

            Icon(imageVector = Icons.Rounded.Delete, contentDescription = "Delete",
                modifier = Modifier.clickable {
                    favouriteViewModel.deleteFavourite(favourite)
                },
                tint = Color.Red.copy(alpha = 0.3f))
        }

    }
}
