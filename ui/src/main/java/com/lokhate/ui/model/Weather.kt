package com.lokhate.ui.model

data class Weather(
    val time: String,
    val weather_code: Int,
    val temperature_2m_max: Double,
    val temperature_2m_min: Double,
)
