
![2](https://github.com/anchanareghu/Android-Weather-App/assets/143755659/158b2100-1485-4e56-b822-250a1ca44fc5)

# Android Weather App

WeatherApp is an Android application that provides real-time weather updates using the Meteo API. The app is built using modern Android development tools and frameworks, including Kotlin, Jetpack Compose, Hilt for dependency injection, and Retrofit for network operations.

## Features

- **Real-Time Weather Updates**: Get current weather information for any location.
- **Search Locations**: Search for weather updates by city name.
- **Swipe to Refresh**: Refresh weather data by swiping down.
- **Clean Architecture**: Uses MVVM (Model-View-ViewModel) architecture.
- **Dependency Injection**: Utilizes Hilt for dependency injection.
- **Composable UI**: Built with Jetpack Compose for a modern and reactive UI.
- **Error Handling**: Proper error handling for network requests.

## Architecture

The app follows the MVVM (Model-View-ViewModel) architecture pattern, which helps to separate the UI code from the business logic and data handling.

- **Model**: Represents the data layer of the app, which includes the data models and the repository.
- **View**: Represents the UI layer of the app, which is built using Jetpack Compose.
- **ViewModel**: Acts as a bridge between the Model and the View, providing data to the UI and handling user interactions.

## Libraries and Tools

- **Kotlin**: The primary programming language used for the app.
- **Jetpack Compose**: Used for building the UI in a declarative way.
- **Hilt**: Used for dependency injection.
- **Retrofit**: Used for making network requests to the OpenWeatherMap API.
- **Coroutines**: Used for asynchronous programming.
- **LiveData**: Used for observing data changes in the ViewModel.
- **Open-Meteo API**: Provides the weather data.
