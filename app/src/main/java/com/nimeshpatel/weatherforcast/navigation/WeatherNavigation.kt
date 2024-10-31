package com.nimeshpatel.weatherforcast.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.nimeshpatel.weatherforcast.screens.about.AboutScreen
import com.nimeshpatel.weatherforcast.screens.favourite.FavouriteScreen
import com.nimeshpatel.weatherforcast.screens.main.MainScreen
import com.nimeshpatel.weatherforcast.screens.main.MainViewModel
import com.nimeshpatel.weatherforcast.screens.search.SearchScreen
import com.nimeshpatel.weatherforcast.screens.settings.SettingScreen
import com.nimeshpatel.weatherforcast.screens.splash.WeatherSplashScreen

/**
 * Created by Nimesh Patel on 28-Sep-24.
 * Purpose:
 */
@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {

        /* Splash Screen */
        composable(route = WeatherScreens.SplashScreen.name) {
            WeatherSplashScreen(navController = navController)
        }


        /* Main Screen*/
        val route = WeatherScreens.MainScreen.name
        composable(route = "$route/{city}",
            arguments = listOf(
                navArgument(name = "city") {
                    type = NavType.StringType
                }
            )
        ) { navBack ->
            navBack.arguments?.getString("city").let { city ->
                val mainViewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    navController = navController,
                    mainViewModel = mainViewModel,
                    city = city ?: "Navsari"
                )
            }
        }

        /* Search Screen */
        composable(WeatherScreens.SearchScreen.name) {
            SearchScreen(navController = navController)
        }

        /* About Screen */
        composable(WeatherScreens.AboutScreen.name) {
            AboutScreen(navController = navController)
        }

        /* Setting Screen */
        composable(WeatherScreens.SettingsScreen.name) {
            SettingScreen(navController = navController)
        }

        /* Favourite Screen */
        composable(WeatherScreens.FavouriteScreen.name) {
            FavouriteScreen(navController = navController)
        }

    }
}