package com.example.weatherapp2.data.repository
import com.example.weatherapp2.data.network.WeatherApiService
import com.example.weatherapp2.model.WeatherResponse
import retrofit2.Call
import retrofit2.Response

class WeatherRepository(
    private val weatherApiService: WeatherApiService,
) {
    suspend fun getWeatherDataForecast(
        lat: Double,
        lon: Double,
        timezone: String,
        units: String
    ): Call<WeatherResponse> {
        return weatherApiService.getWeatherData(lat, lon, timezone, units)
    }

    /* suspend fun saveWeather(weather: WeatherEntity) {
         weatherDao.insertWeather(weather)
     }

     suspend fun getWeatherByDate(date: String): WeatherEntity {
         return weatherDao.getWeatherByDate(date)
     }*/
}
