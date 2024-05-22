package com.example.weatherapp2.data.network

import com.example.weatherapp2.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {
    @GET("forecast")
    fun getWeatherData(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("timezone") timezone: String,
        @Query("temperature_unit") temperatureUnit: String,
        @Query("daily") daily: String = "weathercode,temperature_2m_max,temperature_2m_min"
        ) : Response<WeatherResponse>
}