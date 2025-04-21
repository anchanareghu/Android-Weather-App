package com.example.myweatherapp.presentation


import android.Manifest
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.myweatherapp.data.location.DefaultLocationTracker
import com.example.myweatherapp.presentation.ui.theme.WeatherApplicationTheme
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            if (permissions[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                permissions[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                requestLocationAndLoadWeather()
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show()
            }
        }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
            )
        )

        setContent {
            WeatherApplicationTheme {
                WeatherMain(
                    location = weatherViewModel.cityName,
                    state = weatherViewModel.state,
                    onRefresh = {
                        requestLocationAndLoadWeather()
                    }
                )
                if (weatherViewModel.state.isLoading) {
                    ProgressIndicator()
                }
                weatherViewModel.state.error?.let { error ->
                    ErrorMessage(error)
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun requestLocationAndLoadWeather() {
        CoroutineScope(Dispatchers.IO).launch {
            val location = DefaultLocationTracker(
                locationClient = LocationServices.getFusedLocationProviderClient(this@MainActivity),
                application = application
            ).getCurrentLocation()

            withContext(Dispatchers.Main) {
                if (location != null) {
                    weatherViewModel.loadWeatherInfo(location)
                    weatherViewModel.cityName = latLongToCity(
                        latitude = location.latitude,
                        longitude = location.longitude
                    )
                } else {
                    weatherViewModel.state = weatherViewModel.state.copy(
                        isLoading = false,
                        error = "Couldn't retrieve location. Make sure to grant permission and enable GPS."
                    )
                }
            }
        }
    }

    private fun latLongToCity(latitude: Double, longitude: Double): String {
        return Geocoder(this, Locale.getDefault()).getFromLocation(latitude, longitude, 1)
            ?.firstOrNull()
            ?.locality ?: "Unknown City"
    }
}
