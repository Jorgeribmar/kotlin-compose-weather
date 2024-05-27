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
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier, startDestination: String = Screen.WeatherList.route) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.WeatherList.route) {
            WeatherScreen(navController = navController, lat = 52.52, lon = 13.41, timezone = "Europe/Paris", units = "fahrenheit" )
        }
        composable(Screen.WeatherDetail.route) { backStackEntry ->
            val weatherId = backStackEntry.arguments?.getString("weatherId") ?: "0"
            WeatherDetails(navController = navController, weatherId)
        }
    }
}
