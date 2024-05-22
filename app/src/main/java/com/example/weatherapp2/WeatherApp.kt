package com.example.weatherapp2

import WeatherRepository
import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

var TAG = "WeatherApp"

@HiltAndroidApp
class WeatherApp() : Application()