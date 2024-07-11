package com.example.myweatherapp.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweatherapp.R

@Composable
fun WeatherDetails(modifier: Modifier = Modifier, vector: Int, value: Int, unit: String) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(vector),
            tint = colorResource(id = R.color.grey),
            modifier = modifier.size(24.dp),
            contentDescription = null,
        )
        Spacer(modifier = modifier.height(8.dp))
        Text(
            text = "$value$unit",
            color = colorResource(id = R.color.grey)
        )
    }
}

@Preview
@Composable
fun WeatherDetailsPreview() {
    WeatherDetails(
        vector = R.drawable.humidity,
        value = 60,
        unit = "%"
    )
}