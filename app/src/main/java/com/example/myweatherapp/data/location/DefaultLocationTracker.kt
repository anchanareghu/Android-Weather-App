package com.example.myweatherapp.data.location

import android.Manifest
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.util.Log
import androidx.core.content.ContextCompat
import com.example.myweatherapp.domain.location.LocationTracker
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocationTracker @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationTracker {
    override suspend fun getCurrentLocation(): Location? {
        val hasAccessFineLocationPermission = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val hasAccessCoarseLocationPermission = ContextCompat.checkSelfPermission(
            application, Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        val locationManager =
            application.getSystemService(Context.LOCATION_SERVICE) as LocationManager

        val isGpsEnabled =
            locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
                LocationManager.NETWORK_PROVIDER
            )

        if (!hasAccessCoarseLocationPermission || !hasAccessFineLocationPermission || !isGpsEnabled) {
            Log.e("LocationTracker", "Permissions not granted or GPS not enabled.")
            return null
        }
        return suspendCancellableCoroutine {
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        Log.d("LocationTracker", "Location retrieved: $result")
                        it.resume(result)
                    } else {
                        Log.e("LocationTracker", "Failed to retrieve location.")
                        it.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener { location ->
                    Log.d("LocationTracker", "Location retrieved: $location")
                    it.resume(location)
                }
                addOnFailureListener { exception ->
                    Log.e("LocationTracker", "Error retrieving location: $exception")
                    it.resume(null)
                }
                addOnCanceledListener {
                    Log.e("LocationTracker", "Location retrieval cancelled.")
                    it.cancel()
                }
            }

        }
    }

}
