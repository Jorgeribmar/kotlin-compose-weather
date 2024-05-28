package com.example.weatherapp2.weathers.ui

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.weatherapp2.common.viewmodel.WeatherPrefState
import com.example.weatherapp2.common.viewmodel.WeatherState
import com.example.weatherapp2.common.viewmodel.WeatherViewModel
import com.example.weatherapp2.network.model.Weather
import com.example.weatherapp2.weathers.R

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel ,
    navController: NavHostController,
    lat: Double,
    lon: Double,
    timezone: String,
    units: String
) {
    val weatherState by viewModel.weatherState.collectAsState()
    val weatherPrefState by viewModel.weatherPref.collectAsState()

    LaunchedEffect(Unit) {
        Log.d("WeatherScreen", "Launched effect triggered")
        println(weatherPrefState)
        viewModel.getWeatherPref()
        when (weatherPrefState) {
            is WeatherPrefState.Success -> {
                val data = (weatherPrefState as WeatherPrefState.Success).data
                viewModel.fetchWeather(lat, lon, timezone, data)
            }
            else -> {
                viewModel.fetchWeather(lat, lon, timezone, units)
            }
        }
    }

    when (weatherState) {
        is WeatherState.Loading -> {
            CircularProgressIndicator()
        }

        is WeatherState.Success -> {
            Log.d("WeathetScreen", "Success")
            val data = (weatherState as WeatherState.Success).data
            val weatherList: MutableList<Weather> = mutableListOf()
            data.daily.time.forEachIndexed { index, value ->
                weatherList.add(
                    Weather(
                        time = value,
                        weatherCode = data.daily.weatherCode[index],
                        temperature2mMax = data.daily.temperature2mMax[index],
                        temperature2mMin = data.daily.temperature2mMin[index]
                    )
                )
            }
            when (weatherPrefState) {
                is WeatherPrefState.Success -> {
                    val tempUnit = (weatherPrefState as WeatherPrefState.Success).data
                    Column(
                        modifier = Modifier.padding(64.dp)
                    ) {
                        Log.d("WeatherScreen", "Default value from state : $tempUnit")
                        WeatherPreferenceToggle(defaultValue = tempUnit) {
                            Log.d("WeatherScreen", "Switch changed : $it - Temp Uni: $tempUnit")

                            val pref = if (it) "celsius" else "fahrenheit"
                            viewModel.setWeatherPref(pref)
                            viewModel.fetchWeather(lat, lon, timezone, pref)
                        }
                        WeatherList(navController = navController, list = weatherList)
                    }
                }

                else -> Text(text = stringResource(R.string.error_getting_data))
            }
            println(data)
        }

        is WeatherState.Error -> {
            Text(text = (weatherState as WeatherState.Error).message)
        }
    }
}

fun isSwitchChecked(value: String): Boolean {
    return if (value.lowercase() == "celsius") true else false
}

@Composable
fun WeatherPreferenceToggle(defaultValue: String, onSwitchChanged: (Boolean) -> Unit) {
    Log.d("WeatherScreen", "Toggle value: $defaultValue")
    Row {
        Text(text = "°F")
        Switch(checked = isSwitchChecked(defaultValue), onCheckedChange = onSwitchChanged)
        Text(text = "°C")
    }
}

@Composable
fun WeatherList(navController: NavHostController, list: List<Weather>) {
    Column(
        modifier = Modifier.padding(top = 64.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        list.forEachIndexed { index, weather ->
            WeatherListItem(item = weather, handleClick = {
                navController.navigate("details/${index}")
            })
        }
    }
}