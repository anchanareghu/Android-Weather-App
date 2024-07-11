package com.example.myweatherapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myweatherapp.domain.location.LocationTracker
import com.example.myweatherapp.domain.repository.WeatherRepository
import com.example.myweatherapp.domain.util.Resource
import com.example.myweatherapp.domain.weather.WeatherData
import com.example.myweatherapp.domain.weather.WeatherInfo
import com.example.myweatherapp.domain.weather.WeatherType
import com.example.myweatherapp.presentation.WeatherViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import org.junit.*
import org.junit.rules.RuleChain
import org.junit.rules.TestRule

class ExampleUnitTest{
    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}