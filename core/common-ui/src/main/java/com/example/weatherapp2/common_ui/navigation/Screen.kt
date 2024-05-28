package com.example.weatherapp2.common_ui.navigation

sealed class Screen(val route: String) {
    data object WeatherList: Screen("weather")
    data object WeatherDetail: Screen("details/{weatherId}")
}