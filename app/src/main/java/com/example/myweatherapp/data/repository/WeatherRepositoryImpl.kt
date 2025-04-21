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

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherApi
) : WeatherRepository {
    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getWeather(lat: Double, lon: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeather(
                    latitude = lat,
                    longitude = lon
                ).toWeatherInfo()
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}
