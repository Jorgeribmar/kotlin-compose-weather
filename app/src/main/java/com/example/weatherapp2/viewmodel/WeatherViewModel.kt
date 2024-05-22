package com.example.weatherapp2.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp2.data.repository.WeatherRepository
import com.example.weatherapp2.model.WeatherResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
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
                weatherRepository.getWeatherDataForecast(lat, lon, timezone, units).enqueue(object: Callback<WeatherResponse> {
                    override fun onResponse(
                        call: Call<WeatherResponse>,
                        response: Response<WeatherResponse>
                    ) {
                        if (response.isSuccessful) {
                            val body = response.body()
                            if (body != null) {
                                _weatherState.value = WeatherState.Success(body)
                            }
                        } else {
                            Log.d("WeatherViewModel", "Error fetching data")
                        }
                    }

                    override fun onFailure(p0: Call<WeatherResponse>, p1: Throwable) {
                        Log.d("WeatherViewModel", "Error fetching data ${p1.message}")
                    }
                })
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error(e.message ?: "Unknown Error")
            }
        }
    }


}

sealed class WeatherState {
    object Loading : WeatherState()
    data class Success(val data: WeatherResponse) : WeatherState()
    data class Error(val message: String) : WeatherState()
}

