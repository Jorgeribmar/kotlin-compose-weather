package com.example.weather_detail.ui

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
import androidx.navigation.NavController
import com.example.weather_detail.R
import com.example.weatherapp2.common.viewmodel.WeatherState
import com.example.weatherapp2.common.viewmodel.WeatherViewModel
import com.example.weatherapp2.network.model.Weather

@Composable
fun WeatherDetails( viewModel: WeatherViewModel,navController: NavController, weatherId: String, modifier: Modifier = Modifier ) {
    val weatherList by viewModel.weatherList.collectAsState()

    if (weatherList.isEmpty()) {
        Text(text = stringResource(R.string.error_no_weather_list))
    } else {
        val id = weatherId.toInt()
        val weather = weatherList[id]

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