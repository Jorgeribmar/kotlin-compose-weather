package com.lokhate.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lokhate.ui.model.Weather

@Composable
fun WeatherListItem(item: Weather, handleClick: (Weather) -> Unit, modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .padding(vertical = 24.dp)
            .clickable {
                handleClick(item)
            }
    ) {
        Text(text = item.time)
        Text(text = "Min: ${item.temperature2mMin} Max: ${item.temperature2mMax}")
    }
}