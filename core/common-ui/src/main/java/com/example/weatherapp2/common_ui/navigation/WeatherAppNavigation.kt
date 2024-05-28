package com.example.weatherapp2.common_ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.weatherapp2.common.viewmodel.WeatherViewModel
import com.example.weather_detail.ui.WeatherDetails
import com.example.weatherapp2.weathers.ui.WeatherScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = Screen.WeatherList.route
) {
    val weatherViewModel: WeatherViewModel = hiltViewModel()
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Screen.WeatherList.route) {
            WeatherScreen(
                viewModel = weatherViewModel,
                navController = navController,
                lat = 52.52,
                lon = 13.41,
                timezone = "Europe/Paris",
                units = "fahrenheit"
            )
        }
        composable(Screen.WeatherDetail.route) { backStackEntry ->
            val weatherId = backStackEntry.arguments?.getString("weatherId") ?: "0"
            WeatherDetails(
                viewModel = weatherViewModel,
                navController = navController,
                weatherId
            )
        }
    }
}
