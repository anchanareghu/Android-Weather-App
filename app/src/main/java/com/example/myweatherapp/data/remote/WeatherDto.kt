package com.example.myweatherapp.data.remote

import com.example.myweatherapp.data.remote.WeatherDataDto
import com.squareup.moshi.Json

data class WeatherDto(
//    @field:Json(name = "weather") val weatherData: WeatherDataDto,
    @field:Json(name = "hourly")
    val hourlyData: WeatherDataDto
)



