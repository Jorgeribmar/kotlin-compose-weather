package com.example.weatherapp2.network.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val daily: DailyWeather
)

data class DailyWeather(
    val time: List<String>,
    @SerializedName("weather_code") val weatherCode: List<Int>,
    @SerializedName("temperature_2m_max") val temperature2mMax: List<Double>,
    @SerializedName("temperature_2m_min") val temperature2mMin: List<Double>,
)