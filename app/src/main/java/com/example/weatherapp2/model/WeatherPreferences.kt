package com.example.weatherapp2.model

import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.Update

@Entity(tableName = "weather_preferences")
data class WeatherPreferences(
    @PrimaryKey(autoGenerate = false) val uid: Int = 0,
    @ColumnInfo(name="temperature_unit") val temperatureUnit: String,
)

@Dao
interface WeatherPreferencesDao {
    @Query("SELECT * FROM weather_preferences LIMIT 1")
    suspend fun getFirst(): List<WeatherPreferences>

    @Query("INSERT OR REPLACE INTO weather_preferences (uid, temperature_unit) VALUES (0, :unit)")
    suspend fun update(unit: String)
}