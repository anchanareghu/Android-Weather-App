package com.example.myweatherapp.domain.weather

import androidx.annotation.DrawableRes
import com.example.myweatherapp.R

sealed class WeatherType(
    val weatherDescription: String,
    @DrawableRes val icon: Int
) {
    object ClearSky : WeatherType(
        weatherDescription = "Clear sky",
        icon = R.drawable.sunny
    )
    object MainlyClear : WeatherType(
        weatherDescription = "Mainly clear",
        icon = R.drawable.cloudy
    )
    object PartlyCloudy : WeatherType(
        weatherDescription = "Partly cloudy",
        icon = R.drawable.cloudy
    )
    object Overcast : WeatherType(
        weatherDescription = "Overcast",
        icon = R.drawable.cloud
    )
    object Foggy : WeatherType(
        weatherDescription = "Foggy",
        icon = R.drawable.foggy
    )
    object DepositingRimeFog : WeatherType(
        weatherDescription = "Depositing rime fog",
        icon = R.drawable.foggy
    )
    object LightDrizzle : WeatherType(
        weatherDescription = "Light drizzle",
        icon = R.drawable.rainy
    )
    object ModerateDrizzle : WeatherType(
        weatherDescription = "Moderate drizzle",
        icon = R.drawable.rainy
    )
    object DenseDrizzle : WeatherType(
        weatherDescription = "Dense drizzle",
        icon = R.drawable.rainy
    )
    object LightFreezingDrizzle : WeatherType(
        weatherDescription = "Slight freezing drizzle",
        icon = R.drawable.snowy
    )
    object DenseFreezingDrizzle : WeatherType(
        weatherDescription = "Dense freezing drizzle",
        icon = R.drawable.snowy
    )
    object SlightRain : WeatherType(
        weatherDescription = "Slight rain",
        icon = R.drawable.rainy
    )
    object ModerateRain : WeatherType(
        weatherDescription = "Rainy",
        icon = R.drawable.rainy
    )
    object HeavyRain : WeatherType(
        weatherDescription = "Heavy rain",
        icon = R.drawable.heavyrain
    )
    object HeavyFreezingRain: WeatherType(
        weatherDescription = "Heavy freezing rain",
        icon = R.drawable.snowy
    )
    object SlightSnowFall: WeatherType(
        weatherDescription = "Slight snow fall",
        icon = R.drawable.snowy
    )
    object ModerateSnowFall: WeatherType(
        weatherDescription = "Moderate snow fall",
        icon = R.drawable.snowy
    )
    object HeavySnowFall: WeatherType(
        weatherDescription = "Heavy snow fall",
        icon = R.drawable.heavy_snow
    )
    object SnowGrains: WeatherType(
        weatherDescription = "Snow grains",
        icon = R.drawable.heavy_snow
    )
    object SlightRainShowers: WeatherType(
        weatherDescription = "Slight rain showers",
        icon = R.drawable.rainy
    )
    object ModerateRainShowers: WeatherType(
        weatherDescription = "Moderate rain showers",
        icon = R.drawable.rainy
    )
    object ViolentRainShowers: WeatherType(
        weatherDescription = "Violent rain showers",
        icon = R.drawable.rainy
    )
    object SlightSnowShowers: WeatherType(
        weatherDescription = "Light snow showers",
        icon = R.drawable.snowy
    )
    object HeavySnowShowers: WeatherType(
        weatherDescription = "Heavy snow showers",
        icon = R.drawable.snowy
    )
    object ModerateThunderstorm: WeatherType(
        weatherDescription = "Moderate thunderstorm",
        icon = R.drawable.thunder
    )
    object SlightHailThunderstorm: WeatherType(
        weatherDescription = "Thunderstorm with slight hail",
        icon = R.drawable.thunderstorm
    )
    object HeavyHailThunderstorm: WeatherType(
        weatherDescription = "Thunderstorm with heavy hail",
        icon = R.drawable.thunderstorm
    )

    companion object {
        fun fromWeatherType(code: Int): WeatherType {
            return when(code) {
                0 -> ClearSky
                1 -> MainlyClear
                2 -> PartlyCloudy
                3 -> Overcast
                45 -> Foggy
                48 -> DepositingRimeFog
                51 -> LightDrizzle
                53 -> ModerateDrizzle
                55 -> DenseDrizzle
                56 -> LightFreezingDrizzle
                57 -> DenseFreezingDrizzle
                61 -> SlightRain
                63 -> ModerateRain
                65 -> HeavyRain
                66 -> LightFreezingDrizzle
                67 -> HeavyFreezingRain
                71 -> SlightSnowFall
                73 -> ModerateSnowFall
                75 -> HeavySnowFall
                77 -> SnowGrains
                80 -> SlightRainShowers
                81 -> ModerateRainShowers
                82 -> ViolentRainShowers
                85 -> SlightSnowShowers
                86 -> HeavySnowShowers
                95 -> ModerateThunderstorm
                96 -> SlightHailThunderstorm
                99 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}