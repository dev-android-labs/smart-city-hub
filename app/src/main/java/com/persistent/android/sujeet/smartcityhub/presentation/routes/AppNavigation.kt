package com.persistent.android.sujeet.smartcityhub.presentation.routes

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.persistent.android.sujeet.smartcityhub.presentation.alerts.AlertUiState
import com.persistent.android.sujeet.smartcityhub.presentation.alerts.AlertsScreen
import com.persistent.android.sujeet.smartcityhub.presentation.home.HomeScreen
import com.persistent.android.sujeet.smartcityhub.presentation.home.StatsViewModel
import com.persistent.android.sujeet.smartcityhub.presentation.screens.AQIScreen
import com.persistent.android.sujeet.smartcityhub.presentation.screens.SplashScreen
import com.persistent.android.sujeet.smartcityhub.presentation.weather.WeatherScreen
import com.persistent.android.sujeet.smartcityhub.presentation.weather.WeatherViewModel

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */

@Composable
fun AppNavigation(cityStatsViewModel: StatsViewModel = viewModel()) {

    val navController = rememberNavController()

    NavHost(navController, startDestination = Routes.Splash.name, modifier = Modifier) {

        composable(route = Routes.Splash.name) {
            SplashScreen(navController)
        }

        composable(route = Routes.HomeScreen.name) {

            val uiState = cityStatsViewModel.uiState.collectAsState()

            HomeScreen(uiState.value, cityStatsViewModel, navController)
        }

        composable(route = Routes.WeatherScreen.name) {

            val weatherViewModel = hiltViewModel<WeatherViewModel>()
            val weatherUiState = weatherViewModel.uiState.collectAsState()
            WeatherScreen(weatherUiState.value, weatherViewModel, navController)
        }

        composable(route = Routes.AQIScreen.name) {
            AQIScreen()
        }

        composable(route = Routes.AlertScreen.name) {
            AlertsScreen(AlertUiState())
        }

    }
}

@Preview
@Composable
fun AppNavigationPreview() {
    AppNavigation()
}