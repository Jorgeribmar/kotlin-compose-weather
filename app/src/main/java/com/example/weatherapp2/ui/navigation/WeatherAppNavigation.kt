package com.example.weatherapp2.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp2.ui.WeatherDetails
import com.google.gson.Gson
import com.example.weatherapp2.network.model.Weather
import com.example.weatherapp2.ui.WeatherScreen

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier, startDestination: String = "weather") {

    NavHost(navController = navController, startDestination = startDestination) {
        composable("weather") {
            WeatherScreen(navController = navController, lat = 52.52, lon = 13.41, timezone = "Europe/Paris", units = "fahrenheit" )
        }
        composable("details/{weather}") { backStackEntry ->
            val weatherString = backStackEntry.arguments?.getString("weather")
            val weather = Gson().fromJson<Weather>(weatherString, Weather::class.java)
            WeatherDetails(navController = navController, weather)
        }
    }
}
