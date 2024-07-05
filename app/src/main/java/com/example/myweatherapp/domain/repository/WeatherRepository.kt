package com.example.myweatherapp.domain.repository

import com.example.myweatherapp.domain.util.Resource
import com.example.myweatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeather(lat: Double, lon: Double): Resource<WeatherInfo>
}