package com.example.weatherapp2.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.weatherapp2.R
import com.example.weatherapp2.common.viewmodel.WeatherState
import com.example.weatherapp2.common.viewmodel.WeatherViewModel
import com.example.weatherapp2.network.model.Weather

@Composable
fun WeatherDetails( viewModel: WeatherViewModel,navController: NavController, weatherId: String, modifier: Modifier = Modifier ) {
    val weatherState by viewModel.weatherState.collectAsState()

    when (weatherState) {
        is WeatherState.Loading -> {
            CircularProgressIndicator()
        }

        is WeatherState.Error -> {
            Text(text = "Error")
        }

        is WeatherState.Success -> {
            val data = (weatherState as WeatherState.Success).data
            val id = weatherId.toInt()
            val weather = Weather(
                time = data.daily.time[id],
                weatherCode = data.daily.weatherCode[id],
                temperature2mMax = data.daily.temperature2mMax[id],
                temperature2mMin = data.daily.temperature2mMin[id]
            )

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(vertical = 64.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = weather.time, fontSize = 32.sp, textAlign = TextAlign.Center)
                Text(text = stringResource(R.string.weather_code, weather.weatherCode))
                Text(text = stringResource(
                    R.string.temperature_min_max,
                    weather.temperature2mMin,
                    weather.temperature2mMax
                ), modifier = Modifier.padding(bottom = 32.dp))
                Button(onClick = { navController.navigateUp() }) {
                    Text(text = stringResource(R.string.go_back))
                }
            }
        }
    }
}