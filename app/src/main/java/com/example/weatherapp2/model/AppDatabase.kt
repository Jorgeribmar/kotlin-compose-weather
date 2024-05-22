package com.example.weatherapp2.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [WeatherPreferences::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherPreferencesDao(): WeatherPreferencesDao
}