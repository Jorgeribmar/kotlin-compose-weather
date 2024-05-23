import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.weatherapp2.ui.WeatherDetails
import com.google.gson.Gson
import com.lokhate.ui.model.Weather

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier, startDestination: String = "weather") {

    NavHost(navController = navController, startDestination = startDestination) {
        composable("weather") {
            WeatherScreen(navController = navController, lat = 52.52, lon = 13.41, timezone = "Europe/Paris", units = "fahrenheit" )
        }
        composable("details/{weather}") { backStackEntry ->
            val weatherString = backStackEntry.arguments?.getString("weather")
            val weather = Gson().fromJson<Weather>(weatherString, Weather::class.java)
            // Display details for the selected date
            WeatherDetails(navController = navController, weather)
        }
    }
}
