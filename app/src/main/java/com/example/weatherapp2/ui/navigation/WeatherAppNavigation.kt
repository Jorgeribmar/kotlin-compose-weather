import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavGraph(startDestination: String = "weather") {
    val navController = rememberNavController()
    NavHost(navController, startDestination = startDestination) {
        composable("weather") {
            WeatherScreen(viewModel = hiltViewModel(), lat = 52.52, lon = 13.41, timezone = "Europe/Paris", units = "fahrenheit" )
        }
        composable("details/{date}") { backStackEntry ->
            val date = backStackEntry.arguments?.getString("date")
            // Display details for the selected date
        }
    }
}
