package com.example.weatherapp2.data.network

import com.example.weatherapp2.data.repository.WeatherRepository
import com.example.weatherapp2.model.WeatherPreferencesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitWeatherModule {


    @Provides
    @Singleton
    fun provideRetrofitWeather(): WeatherApiService {
        val t = Retrofit.Builder()
            .baseUrl("https://api.open-meteo.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiService::class.java)
        return t

    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApiService, weatherPreferencesDao: WeatherPreferencesDao): WeatherRepository {
        return WeatherRepository(weatherApi, weatherPreferencesDao)
    }


}
