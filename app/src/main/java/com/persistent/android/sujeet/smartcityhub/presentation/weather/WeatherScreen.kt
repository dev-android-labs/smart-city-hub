package com.persistent.android.sujeet.smartcityhub.presentation.weather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.persistent.android.sujeet.smartcityhub.presentation.components.ForecastListComponent
import com.persistent.android.sujeet.smartcityhub.presentation.components.WeatherCard
import com.persistent.android.sujeet.smartcityhub.presentation.home.StatsEvent
import com.persistent.android.sujeet.smartcityhub.presentation.home.StatsViewModel

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
@Composable
fun WeatherScreen(
    state: WeatherUiState = WeatherUiState(),
    viewModel: StatsViewModel = viewModel(),
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Weather",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (state.isLoading) {
            CircularProgressIndicator(modifier = Modifier.padding(32.dp))
            Text("Loading weather data...", style = MaterialTheme.typography.bodyLarge)
        } else if (state.error != null) {
            Text(
                text = "Error: ${state.error}",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = {
                viewModel.onIntent(StatsEvent.Refresh(state.city))
            }) {
                Text("Retry")
            }
        } else if (state.weatherData != null) {
            WeatherCard(weather = state.weatherData)
            Spacer(modifier = Modifier.height(16.dp))

            state.forecasts?.let { forecasts ->
                if (forecasts.isNotEmpty()) {
                    Text(
                        text = "5-Day Forecast",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    )
                    ForecastListComponent(forecasts = forecasts)
                } else {
                    Text("No forecast data available.", modifier = Modifier.padding(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                viewModel.onIntent(StatsEvent.Refresh(state.city))
            }) {
                Text("Refresh Weather")
            }
        } else {
            Text(
                "No weather data available. Please check your internet connection or try again.",
                modifier = Modifier.padding(16.dp)
            )
            Button(onClick = {
                viewModel.onIntent(StatsEvent.Refresh(state.city))
            }) {
                Text("Load Weather")
            }
        }
    }
}

@Preview
@Composable
fun WeatherScreenPreview() {
    WeatherScreen()
}