package com.example.weatherapp2.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.lokhate.ui.model.Weather

@Composable
fun WeatherDetails(navController: NavController, weather: Weather, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 64.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(text = weather.time, fontSize = 32.sp, textAlign = TextAlign.Center)
        Text(text = "Weather code: ${weather.weather_code}")
        Text(text = "Temperature : min: ${weather.temperature_2m_min} - max: ${weather.temperature_2m_max}", modifier = Modifier.padding(bottom = 32.dp))
        Button(onClick = { navController.navigateUp() }) {
            Text(text = "Go back")
        }
    }
}