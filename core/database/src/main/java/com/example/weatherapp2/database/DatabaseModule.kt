package com.example.weatherapp2.database

import android.app.Application
import androidx.room.Room
import com.example.weatherapp2.database.model.WeatherPreferencesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "weather_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideWeatherDao(db: AppDatabase): WeatherPreferencesDao = db.weatherPreferencesDao()
}