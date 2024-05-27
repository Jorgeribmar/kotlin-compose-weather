package com.example.weatherapp2.database.repository

import com.example.weatherapp2.database.model.WeatherPreferences
import com.example.weatherapp2.database.model.WeatherPreferencesDao

class WeatherDatabaseRepository(
    private val weatherPreferencesDao: WeatherPreferencesDao
) {

    suspend fun saveWeatherPreference(temperatureUnit: String) {
        weatherPreferencesDao.update(temperatureUnit)
    }

    suspend fun getWeatherPreference(): WeatherPreferences {
        val result = weatherPreferencesDao.getFirst()
        return if (result.isEmpty()) WeatherPreferences(
            temperatureUnit = "celsius"
        ) else result[0]
    }
}
