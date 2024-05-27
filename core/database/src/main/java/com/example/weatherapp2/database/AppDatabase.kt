package com.example.weatherapp2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.weatherapp2.database.model.WeatherPreferences
import com.example.weatherapp2.database.model.WeatherPreferencesDao

@Database(entities = [WeatherPreferences::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherPreferencesDao(): WeatherPreferencesDao
}