package com.example.weatherapp2.network

import com.example.weatherapp2.network.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast")
    suspend fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("timezone") timezone: String,
        @Query("temperature_unit") temperatureUnit: String,
        @Query("daily") daily: String = "weather_code,temperature_2m_max,temperature_2m_min"
        ) : WeatherResponse
}