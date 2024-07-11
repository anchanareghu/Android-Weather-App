package com.example.myweatherapp.presentation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.R
import com.example.myweatherapp.domain.weather.WeatherData
import com.example.myweatherapp.domain.weather.WeatherInfo
import com.example.myweatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshIndicator
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlin.math.roundToInt

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherMain(
    state: WeatherState,
    location: String,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = state.isLoading)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(R.color.grey)),
        contentAlignment = Alignment.Center
    ) {
        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = onRefresh,
            indicator = { state, trigger ->
                SwipeRefreshIndicator(
                    state = state,
                    refreshTriggerDistance = trigger,
                    scale = true,
                    contentColor = colorResource(R.color.yale_blue),
                    backgroundColor = colorResource(R.color.grey)
                )
            }
        ) {
            state.weatherInfo?.currentWeatherData?.let { data ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(colorResource(R.color.grey)),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                        ) {
                            Image(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(colorResource(R.color.yale_blue)),
                                modifier = Modifier.size(24.dp)
                            )
                            Text(
                                text = location,
                                color = colorResource(R.color.yale_blue),
                                fontSize = 18.sp,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .padding(start = 4.dp)
                            )
                        }
                        Text(
                            text = LocalDateTime.now()
                                .format(DateTimeFormatter.ofPattern("EEE, hh:mm a")),
                            color = colorResource(R.color.grey),
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .background(colorResource(R.color.yale_blue))
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "${data.temperature.roundToInt()}°C",
                            color = colorResource(id = R.color.yale_blue),
                            fontSize = 72.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(top = 16.dp)
                        )

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 16.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(data.weatherType.icon),
                                contentDescription = null,
                                colorFilter = ColorFilter.tint(colorResource(R.color.yale_blue)),
                                modifier = Modifier.size(48.dp)
                            )
                            Text(
                                text = data.weatherType.weatherDescription,
                                fontWeight = FontWeight.Bold,
                                color = colorResource(R.color.yale_blue),
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }

                        ElevatedCard(
                            colors = CardDefaults.cardColors(
                                containerColor = colorResource(R.color.yale_blue),
                                contentColor = colorResource(R.color.grey)
                            ),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 8.dp,
                                hoveredElevation = 28.dp
                            ),
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                horizontalArrangement = Arrangement.SpaceAround,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                WeatherDetails(
                                    vector = R.drawable.humidity,
                                    value = data.humidity.roundToInt(),
                                    unit = "%"
                                )
                                WeatherDetails(
                                    vector = R.drawable.pressure,
                                    value = data.pressure.roundToInt(),
                                    unit = "hPa"
                                )
                                WeatherDetails(
                                    vector = R.drawable.wind,
                                    value = data.windSpeed.roundToInt(),
                                    unit = "km/h"
                                )
                            }
                        }

                        WeatherForecastCard(state = state, modifier = Modifier.fillMaxWidth())
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun WeatherForecastCard(
    modifier: Modifier = Modifier,
    state: WeatherState
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Hourly Forecast",
            color = colorResource(id = R.color.yale_blue),
            fontWeight = FontWeight.Bold,
        )
        state.weatherInfo?.weatherDataPerDay?.get(0)?.let { data ->
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                items(data) { weatherData ->
                    HourlyForecastCard(
                        modifier = Modifier.padding(8.dp),
                        data = weatherData
                    )
                }
            }
        }
        Text(
            text = "Weekly Forecast",
            color = colorResource(id = R.color.yale_blue),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )
        state.weatherInfo?.weatherDataPerDay?.let { weatherDataPerDay ->
            LazyRow(
                modifier = Modifier
                    .fillMaxHeight(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val nextDayForecasts = weatherDataPerDay.entries.drop(1)
                items(nextDayForecasts) { (day, data) ->
                    data?.let {
                        DailyForecastCard(
                            modifier = Modifier,
                            data = it[1]
                        )
                    }
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HourlyForecastCard(
    modifier: Modifier = Modifier,
    data: WeatherData
) {
    val formattedTime = remember(data) {
        data.time.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formattedTime,
            color = colorResource(id = R.color.yale_blue),
        )
        Spacer(modifier = modifier.height(8.dp))
        Image(
            painter = painterResource(data.weatherType.icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(colorResource(id = R.color.yale_blue)),
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = "${data.temperature}°",
            color = colorResource(id = R.color.yale_blue),
        )

    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DailyForecastCard(
    modifier: Modifier = Modifier,
    data: WeatherData
) {
    ElevatedCard(
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.grey),
            contentColor = colorResource(id = R.color.yale_blue)
        ),
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = data.time.format(DateTimeFormatter.ofPattern("EEE, dd MMM")),
                fontSize = 12.sp,
                color = colorResource(id = R.color.yale_blue),
            )
            Spacer(modifier = modifier.height(8.dp))

            Image(
                painter = painterResource(data.weatherType.icon),
                contentDescription = null,
                colorFilter = ColorFilter.tint(colorResource(id = R.color.yale_blue)),
            )
            Spacer(modifier = modifier.height(8.dp))

            Text(
                text = "${data.temperature}°",
                color = colorResource(id = R.color.yale_blue),
            )
        }
    }
}

@Composable
fun ProgressIndicator() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = colorResource(id = R.color.yale_blue)
        )
    }
}

@Composable
fun ErrorMessage(error: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = error,
            color = Color.Red,
            textAlign = TextAlign.Center
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun WeatherAppPreview() {
    WeatherMain(
        state = WeatherState(
            weatherInfo = WeatherInfo(
                currentWeatherData = WeatherData(
                    time = LocalDateTime.now(),
                    temperature = 27.0,
                    humidity = 9.0,
                    pressure = 987.0,
                    windSpeed = 9080.0,
                    weatherType = WeatherType.fromWeatherType(7)
                ),
                weatherDataPerDay = mapOf(
                    0 to listOf(
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperature = 27.0,
                            humidity = 9.0,
                            pressure = 987.0,
                            windSpeed = 9080.0,
                            weatherType = WeatherType.fromWeatherType(7)
                        ),
                        WeatherData(
                            time = LocalDateTime.now(),
                            temperature = 27.0,
                            humidity = 9.0,
                            pressure = 987.0,
                            windSpeed = 9080.0,
                            weatherType = WeatherType.fromWeatherType(9)
                        )
                    )
                )
            )
        ),
        location = "India",
        onRefresh = {},
    )

}