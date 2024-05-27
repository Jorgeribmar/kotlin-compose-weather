package com.lokhate.ui.model

import com.google.gson.annotations.SerializedName


data class Weather(
    val time: String,
    @SerializedName("weather_code") val weatherCode: Int,
    @SerializedName("temperature_2m_max") val temperature2mMax: Double,
    @SerializedName("temperature_2m_min") val temperature2mMin: Double
)
