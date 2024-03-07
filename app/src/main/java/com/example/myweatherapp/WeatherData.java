package com.example.myweatherapp;

import org.json.JSONException;
import org.json.JSONObject;

public class WeatherData {

    private String temperature;
    private String condition;
    private String location;
    private String weather_icon;
    private String humidity;
    private String feels_like;

    private String wind_speed;
    private int weatherId;

    public static WeatherData fromJson(JSONObject jsonObject) {
        WeatherData weatherData = new WeatherData();
        try {
            weatherData.location = jsonObject.getString("name");
            weatherData.weatherId = jsonObject.getJSONArray("weather").getJSONObject(0).getInt("id");
            weatherData.condition = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
            weatherData.weather_icon = updateWeatherIcon(weatherData.weatherId);
            double temp = jsonObject.getJSONObject("main").getDouble("temp") - 273.15;
            int rounded_temp_value = (int) Math.rint(temp);
            weatherData.temperature = Integer.toString(rounded_temp_value);

            weatherData.humidity =jsonObject.getJSONObject("main").getString("humidity");
            weatherData.feels_like =  String.valueOf((int) Math.rint(Double.parseDouble(jsonObject.getJSONObject("main").getString("feels_like")) - 273.15));
            weatherData.wind_speed = jsonObject.getJSONObject("wind").getString("speed");


            return weatherData;

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static String updateWeatherIcon(int weatherId) {
        if (weatherId >= 0 && weatherId < 300) {
            return "thunderstorm";
        } else if (weatherId >= 300 && weatherId < 500) {
            return "rainy";
        } else if (weatherId >= 500 && weatherId < 600) {
            return "heavyrain";
        } else if (weatherId >= 600 && weatherId <= 700) {
            return "snowy";
        } else if (weatherId >= 701 && weatherId <= 771) {
            return "foggy";
        } else if (weatherId >= 772 && weatherId < 800) {
            return "overcast";
        } else if (weatherId == 800) {
            return "cloudy";
        } else if (weatherId == 801 || weatherId == 802) {
            return "cloud";
        } else if (weatherId == 803 || weatherId == 804) {
            return "cloud";
        } else if (weatherId >= 900 && weatherId <= 902) {
            return "thunder";
        } else if (weatherId == 903) {
            return "snow";
        } else if (weatherId == 904) {
            return "sunny";
        } else if (weatherId >= 905 && weatherId <= 1000) {
            return "thunder";
        }
        return "dunno";
    }

    public String getTemperature() {
        return temperature + "°C";
    }

    public String getCondition() {
        return condition;
    }

    public String getLocation() {
        return location;
    }

    public String getWeather_icon() {
        return weather_icon;
    }

    public String getHumidity() {
        return humidity + "%";
    }

    public String getFeels_like() {
        return feels_like + "°C";
    }

    public String getWind_speed() {
        return wind_speed + "mi/h";
    }
}
