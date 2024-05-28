package com.example.weatherapp2.network.model

import com.google.gson.annotations.SerializedName

data class Weather(
    val time: String,
    @SerializedName("weather_code") val weatherCode: Int,
    @SerializedName("temperature_2m_max") val temperature2mMax: Double,
    @SerializedName("temperature_2m_min") val temperature2mMin: Double
)

fun convertWeatherResponseToWeather(response: WeatherResponse): List<Weather> {
    return response.daily.time.mapIndexed{ index, value ->
        Weather(time = value, weatherCode = response.daily.weatherCode[index], temperature2mMin = response.daily.temperature2mMin[index], temperature2mMax = response.daily.temperature2mMax[index])
    }
}