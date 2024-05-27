package com.example.weatherapp2.ui.navigation

sealed class Screen(val route: String) {
    data object WeatherList: Screen("weather")
    data object WeatherDetail: Screen("details/{weatherId}")
}