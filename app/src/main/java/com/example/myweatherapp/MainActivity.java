package com.example.myweatherapp;

import static android.Manifest.permission.ACCESS_BACKGROUND_LOCATION;
import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

import java.util.Calendar;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends Activity {
    //    final String API_key = "5a4f6b81262efb3956c49a7b94fd3288";
    final String API_key = "aa8a4b6bda9d86fd0e2ca3847276fbb4";

    final String openWeatherMap_URL = "https://api.openweathermap.org/data/2.5/weather";

    ProgressBar progressBar;

    SearchView searchView;

    int REQUEST_CODE = 101;

    TextView temperature, condition, location, feels_like, humidity, wind_speed, feels_like_text, humidity_text, wind_speed_text;
    ImageView weather_icon, wind_img, humidity_img, thermostat_img;


    RelativeLayout relativeLayout;

    LocationManager locationManager;
    LocationListener locationListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_main);

        progressBar = findViewById(R.id.progress_bar);

        location = findViewById(R.id.location);
        temperature = findViewById(R.id.temperature);
        condition = findViewById(R.id.condition);
        weather_icon = findViewById(R.id.weather_img);

        wind_img = findViewById(R.id.wind_img);
        humidity_img = findViewById(R.id.humidity_img);
        thermostat_img = findViewById(R.id.thermostat_img);

        feels_like = findViewById(R.id.feels_like);
        humidity = findViewById(R.id.humidity);
        wind_speed = findViewById(R.id.wind_speed);
        feels_like_text = findViewById(R.id.feels_like_text);
        humidity_text = findViewById(R.id.humidity_text);
        wind_speed_text = findViewById(R.id.wind_speed_text);

        relativeLayout = findViewById(R.id.relative_layout);
        searchView = (SearchView) findViewById(R.id.search_bar);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                RequestParams requestParams = new RequestParams();
                requestParams.put("q", query);
                requestParams.put("appid", API_key);
                getWeatherFromAPI(requestParams);

                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        getCurrentLocationWeather();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getCurrentLocationWeather();
    }

    private void getCurrentLocationWeather() {
        locationManager = (LocationManager) getSystemService((Context.LOCATION_SERVICE));
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
                String latitude = String.valueOf(location.getLatitude());
                String longitude = String.valueOf(location.getLongitude());

                RequestParams requestParams = new RequestParams();
                requestParams.put("lat", latitude);
                requestParams.put("lon", longitude);
                requestParams.put("appid", API_key);
                getWeatherFromAPI(requestParams);
            }

        };
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, REQUEST_CODE);
            return;
        }
//        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1000, locationListener);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1000, locationListener);

    }

    private void getWeatherFromAPI(RequestParams requestParams) {
        AsyncHttpClient client = new AsyncHttpClient();
        client.get(openWeatherMap_URL, requestParams, new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        progressBar.setVisibility(View.GONE);
                        WeatherData weatherData = WeatherData.fromJson(response);
                        updateUI(weatherData);
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        super.onFailure(statusCode, headers, throwable, errorResponse);
                        Toast.makeText(MainActivity.this, "ERROR OCCURRED", Toast.LENGTH_SHORT).show();

                    }
                }
        );
    }

    private void updateUI(WeatherData weatherData) {

        weather_icon.setImageResource(getResources().getIdentifier(weatherData.getWeather_icon(), "drawable", getPackageName()));
        temperature.setText(weatherData.getTemperature());
        condition.setText(weatherData.getCondition());
        location.setText(weatherData.getLocation());

        wind_speed.setText(weatherData.getWind_speed());
        humidity.setText(weatherData.getHumidity());
        feels_like.setText(weatherData.getFeels_like());


        Calendar calendar = Calendar.getInstance();
        int timeOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        if (timeOfDay < 12) {
            relativeLayout.setBackgroundResource(R.drawable.fog);
        } else if (timeOfDay < 16) {
            relativeLayout.setBackgroundResource(R.drawable.fog);
        } else if (timeOfDay < 21) {
            relativeLayout.setBackgroundResource(R.drawable.night);
        } else {
            relativeLayout.setBackgroundResource(R.drawable.night);

            location.setTextColor(Color.WHITE);
            condition.setTextColor(Color.WHITE);
            temperature.setTextColor(Color.WHITE);

            weather_icon.setImageTintList(ColorStateList.valueOf(Color.WHITE));
            wind_speed.setTextColor(Color.WHITE);
            humidity.setTextColor(Color.WHITE);
            feels_like.setTextColor(Color.WHITE);
            wind_speed_text.setTextColor(Color.WHITE);
            humidity_text.setTextColor(Color.WHITE);
            feels_like_text.setTextColor(Color.WHITE);
            thermostat_img.setImageTintList(ColorStateList.valueOf(Color.WHITE));
            humidity_img.setImageTintList(ColorStateList.valueOf(Color.WHITE));
            wind_img.setImageTintList(ColorStateList.valueOf(Color.WHITE));
        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (locationManager != null) {
            locationManager.removeUpdates(locationListener);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == RESULT_OK) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocationWeather();
            } else {
                Toast.makeText(MainActivity.this, "Location Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
