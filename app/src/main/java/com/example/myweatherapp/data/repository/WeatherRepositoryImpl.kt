package com.example.myweatherapp.data.repository

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.myweatherapp.data.mappers.toWeatherInfo
import com.example.myweatherapp.data.remote.WeatherApi
import com.example.myweatherapp.domain.util.Resource
import com.example.myweatherapp.domain.weather.WeatherInfo
import com.example.myweatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

//class WeatherRepositoryImpl @Inject constructor(
//    private val api: WeatherApi
//) : WeatherRepository {
//    @RequiresApi(Build.VERSION_CODES.O)
//    override suspend fun getWeather(lat: Double, lon: Double): Resource<WeatherInfo> {
//        return try {
//            val response = api.getWeather(latitude = lat, longitude = lon)
//            Log.d("WeatherRepository", "API response: $response")
//            Resource.Success(
//                data = response.toWeatherInfo()
//            )
//        } catch (e: Exception) {
//            Log.e("WeatherRepository", "API call failed: ${e.message}")
//            Resource.Error(e.message ?: "An unknown error occurred.")
//        }
//    }
//}
class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeather(lat: Double, lon: Double): Resource<WeatherInfo> {
        return try {
            val apiKey = "aa8a4b6bda9d86fd0e2ca3847276fbb4"
            Resource.Success(
                data = api.getWeather(
                    latitude = lat,
                    longitude = lon,
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}