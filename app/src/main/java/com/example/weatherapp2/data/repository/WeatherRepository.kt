import com.example.weatherapp2.data.network.WeatherApiService
import com.example.weatherapp2.model.WeatherResponse
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(
    private val weatherApiService: WeatherApiService,
    /*private val weatherDao: WeatherDao*/
) {
    fun getWeatherDataForecast(lat: Double, lon: Double, timezone: String, units: String): Response<WeatherResponse> {
        return weatherApiService.getWeatherData(lat, lon, timezone, units)
    }

   /* suspend fun saveWeather(weather: WeatherEntity) {
        weatherDao.insertWeather(weather)
    }

    suspend fun getWeatherByDate(date: String): WeatherEntity {
        return weatherDao.getWeatherByDate(date)
    }*/
}
