package com.example.weatherapp2.data.repository
import com.example.weatherapp2.data.network.WeatherApiService
import com.example.weatherapp2.model.WeatherPreferences
import com.example.weatherapp2.model.WeatherPreferencesDao
import com.example.weatherapp2.model.WeatherResponse
import retrofit2.Call
import retrofit2.Response

class WeatherRepository(
    private val weatherApiService: WeatherApiService,
    private val weatherPreferencesDao: WeatherPreferencesDao
) {
    suspend fun getWeatherDataForecast(
        lat: Double,
        lon: Double,
        timezone: String,
        units: String
    ): Call<WeatherResponse> {
        return weatherApiService.getWeatherData(lat, lon, timezone, units)
    }

     suspend fun saveWeatherPreference(temperatureUnit: String) {
         weatherPreferencesDao.update(temperatureUnit)
     }

     suspend fun getWeatherPreference(): WeatherPreferences {
         val result = weatherPreferencesDao.getFirst()
         return if (result.isEmpty()) WeatherPreferences(temperatureUnit = "celsius") else result[0]
     }
}
