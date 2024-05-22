import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.weatherapp2.viewmodel.WeatherState
import com.example.weatherapp2.viewmodel.WeatherViewModel

@Composable
fun WeatherScreen(viewModel: WeatherViewModel,lat: Double, lon: Double, timezone: String, units: String) {
    val weatherState by viewModel.weatherState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.fetchWeather(lat, lon, timezone, units )
    }

    when (weatherState) {
        is WeatherState.Loading -> {
            CircularProgressIndicator()
        }
        is WeatherState.Success -> {

            val data = (weatherState as WeatherState.Success).data

            println(data.toString())
        }
        is WeatherState.Error -> {
            Text(text = (weatherState as WeatherState.Error).message)
        }
    }
}
