package com.example.weatherapp2.common.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp2.database.repository.WeatherDatabaseRepository
import com.example.weatherapp2.network.model.Weather
import com.example.weatherapp2.network.model.WeatherResponse
import com.example.weatherapp2.network.repository.WeatherNetworkRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherDBRepository: WeatherDatabaseRepository,
    private val weatherNetworkRepository: WeatherNetworkRepository
) : ViewModel() {

    private val _weatherState = MutableStateFlow<WeatherState>(WeatherState.Loading)
    val weatherState: StateFlow<WeatherState> = _weatherState
    private val _weatherPrefState = MutableStateFlow<WeatherPrefState>(WeatherPrefState.Loading)
    val weatherPref: StateFlow<WeatherPrefState> = _weatherPrefState
    private val _weatherList = MutableStateFlow<List<Weather>>(emptyList())
    val weatherList: StateFlow<List<Weather>> = _weatherList

    fun getWeatherPref() {
        viewModelScope.launch {
            try {
                val pref = weatherDBRepository.getWeatherPreference()
                _weatherPrefState.value = WeatherPrefState.Success(pref.temperatureUnit)
            }  catch (e: Exception) {
                _weatherPrefState.value = WeatherPrefState.Error(e.message ?: "error")
                Log.d("WeatherViewModel", "Error fetching user preferences : ${e.message}")
            }
        }
    }

    fun setWeatherPref(pref: String) {
        viewModelScope.launch {
            try {
                weatherDBRepository.saveWeatherPreference(pref)
                _weatherPrefState.value = WeatherPrefState.Success(pref)
            } catch (e: Exception) {
                Log.d("WeatherViewModel", "Error setting user preferences : ${e.message}")
            }
        }
    }

    fun fetchWeather(lat: Double, lon: Double, timezone: String, units: String) {
        viewModelScope.launch {
            try {
                val response =
                    weatherNetworkRepository.getWeatherDataForecast(lat, lon, timezone, units)
                _weatherState.value = WeatherState.Success(response)
                val list: MutableList<Weather> = mutableListOf()
                response.daily.time.forEachIndexed { index, value ->
                    list.add(Weather(time = value, weatherCode = response.daily.weatherCode[index], temperature2mMax = response.daily.temperature2mMax[index], temperature2mMin = response.daily.temperature2mMin[index]))
                }
                Log.d("ViewModel", "Saving to list")
                println(list)
                _weatherList.value = list
            } catch (e: Exception) {
                _weatherState.value = WeatherState.Error(e.message ?: "Unknown Error")
            }
        }
    }


}

sealed class WeatherState {
    data object Loading : WeatherState()
    data class Success(val data: WeatherResponse) : WeatherState()
    data class Error(val message: String) : WeatherState()
}

sealed class WeatherPrefState {
    data object Loading : WeatherPrefState()
    data class Success(val data: String) : WeatherPrefState()
    data class Error(val message: String) : WeatherPrefState()
}