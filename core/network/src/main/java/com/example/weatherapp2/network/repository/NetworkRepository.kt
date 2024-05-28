package com.example.weatherapp2.network.repository

import com.example.weatherapp2.network.WeatherApiService
import com.example.weatherapp2.network.model.WeatherResponse
import javax.inject.Inject

class WeatherNetworkRepository @Inject constructor(
    private val weatherApiService: WeatherApiService,
) {
    suspend fun getWeatherDataForecast(
        lat: Double,
        lon: Double,
        timezone: String,
        units: String
    ): WeatherResponse {
        return weatherApiService.getWeatherData(lat, lon, timezone, units)
    }
}
