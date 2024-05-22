package com.example.weatherapp2

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

var TAG = "WeatherApp"

@HiltAndroidApp
class WeatherApp() : Application()