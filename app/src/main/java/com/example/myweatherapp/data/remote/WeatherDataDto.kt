package com.example.myweatherapp.data.remote

import com.squareup.moshi.Json

data class WeatherDataDto(
    val time: List<String>,
    @field:Json(name = "temperature_2m")
    val temperatures: List<Double>,
    @field:Json(name = "weathercode")
    val weatherCodes: List<Int>,
    @field:Json(name = "pressure_msl")
    val pressures: List<Double>,
    @field:Json(name = "windspeed_10m")
    val windSpeeds: List<Double>,
    @field:Json(name = "relativehumidity_2m")
    val humidities: List<Double>
)
//data class WeatherDataDto(
//    @field:Json(name = "dt") val time: Long,
//    @field:Json(name = "temp") val temperature: Double,
//    @field:Json(name = "humidity") val humidity: Int,
//    @field:Json(name = "pressure") val pressure: Int,
//    @field:Json(name = "wind_speed") val windSpeed: Double,
//    @field:Json(name = "visibility") val visibility: Double,
//    @field:Json(name = "weather") val weather: List<WeatherConditionDto>
//)
//
//data class WeatherConditionDto(
//    @field:Json(name = "id") val id: Int,
//    @field:Json(name = "main") val main: String,
//    @field:Json(name = "description") val description: String,
//    @field:Json(name = "icon") val icon: String
//)