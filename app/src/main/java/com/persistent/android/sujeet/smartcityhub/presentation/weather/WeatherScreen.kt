package com.persistent.android.sujeet.smartcityhub.presentation.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.persistent.android.sujeet.smartcityhub.presentation.components.ForecastListComponent
import com.persistent.android.sujeet.smartcityhub.presentation.components.LoadingIndicator
import com.persistent.android.sujeet.smartcityhub.presentation.components.ShowError
import com.persistent.android.sujeet.smartcityhub.presentation.components.WeatherCard
import com.persistent.android.sujeet.smartcityhub.presentation.home.ViewEffects

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    state: WeatherUiState = WeatherUiState(),
    viewModel: WeatherViewModel = viewModel(),
    navController: NavController = rememberNavController(),
) {

    LaunchedEffect(Unit) {
        viewModel.viewEffects.collect { viewEffect ->
            when (viewEffect) {
                ViewEffects.NavigateBack -> {
                    navController.popBackStack()
                }

                else -> {}
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Weather") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                navigationIcon = {
                    Button(onClick = {
                        viewModel.onIntent(WeatherEvent.BackClicked)
                    }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    Button(onClick = {
                        viewModel.onIntent(WeatherEvent.Refresh(state.city))
                    }) {
                        Icon(Icons.Default.Refresh, contentDescription = "Refresh")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.primaryContainer)
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (state.isLoading) {
                    LoadingIndicator("Loading weather data...")
                } else if (state.error != null) {
                    ShowError(state.error) {
                        viewModel.onIntent(WeatherEvent.RefreshWeather(state.city))
                    }
                } else if (state.weather != null) {
                    WeatherCard(weather = state.weather)
                    Spacer(modifier = Modifier.height(16.dp))

                    state.forecasts?.let { forecasts ->
                        if (forecasts.isNotEmpty()) {
                            Text(
                                text = "Forecast",
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
                        viewModel.onIntent(WeatherEvent.RefreshForecast(state.city))
                    }) {
                        Text("Refresh Forecast")
                    }
                } else {
                    ShowError(
                        "No weather data available. Please check your internet connection or try again.",
                        "Load Weather"
                    ) {
                        viewModel.onIntent(WeatherEvent.RefreshWeather(state.city))
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun WeatherScreenPreview() {
    WeatherScreen()
}