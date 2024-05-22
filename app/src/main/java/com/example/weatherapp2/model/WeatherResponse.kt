package com.example.weatherapp2.model

data class WeatherResponse(
    val daily: DailyWeather
)

data class DailyWeather(
    val time: List<String>,
    val weather_code: List<Int>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
)

data class Weather(
    val time: String,
    val weather_code: Int,
    val temperature_2m_max: Double,
    val temperature_2m_min: Double,
)
