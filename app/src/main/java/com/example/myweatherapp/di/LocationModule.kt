package com.example.weatherapplication.di

import com.example.myweatherapp.data.location.DefaultLocationTracker
import com.example.myweatherapp.domain.location.LocationTracker
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationService(
        defaultLocationTracker: DefaultLocationTracker
    ): LocationTracker
}
