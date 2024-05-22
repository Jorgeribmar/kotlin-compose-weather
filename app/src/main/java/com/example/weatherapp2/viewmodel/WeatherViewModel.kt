package com.example.weatherapp2.viewmodel

import WeatherRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp2.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState

    fun fetchWeather(lat: Double, lon: Double, timezone: String, units: String) {
        viewModelScope.launch {
            try {
                val response = weatherRepository.getWeatherDataForecast(lat, lon, timezone, units)
                _weatherState.value = WeatherState.Success(response)
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error(e.message ?: "Unknown Error")
            }
        }
    }


}

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val data: Response<WeatherResponse>) : WeatherState()
    data class Error(val message: String) : WeatherState()
}

