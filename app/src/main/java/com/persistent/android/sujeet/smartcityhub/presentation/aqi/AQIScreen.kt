package com.persistent.android.sujeet.smartcityhub.presentation.aqi

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.persistent.android.sujeet.smartcityhub.presentation.components.AirQualityComponent
import com.persistent.android.sujeet.smartcityhub.presentation.components.AppBarTop
import com.persistent.android.sujeet.smartcityhub.presentation.components.LoadingIndicator
import com.persistent.android.sujeet.smartcityhub.presentation.components.ShowError
import com.persistent.android.sujeet.smartcityhub.presentation.routes.AppEvent
import com.persistent.android.sujeet.smartcityhub.presentation.routes.ViewEffects

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
@Composable
fun AQIScreen(
    uiState: AirQualityUiState = AirQualityUiState(),
    viewModel: AirQualityViewModel = viewModel(),
    navController: NavHostController = rememberNavController(),
) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.viewEffects.collect { viewEffect ->
            when (viewEffect) {
                ViewEffects.NavigateBack -> {
                    navController.popBackStack()
                }

                is ViewEffects.ShowToast -> {
                    Toast.makeText(context, viewEffect.message, Toast.LENGTH_SHORT).show()
                }

                else -> {}
            }
        }

    }

    Scaffold(
        topBar = {
            AppBarTop(
                "Air Quality Index",
                backEnabled = true,
                refreshEnabled = true,
                settingsEnabled = true
            ) { actionEvent ->
                when (actionEvent) {
                    is AppEvent.BackClicked -> {
                        viewModel.onIntent(AppEvent.BackClicked)
                    }

                    is AppEvent.RefreshClicked -> {
                        viewModel.onIntent(AppEvent.RefreshClicked)
                    }

                    is AppEvent.SettingClicked -> {
                        Log.d("TAG", "WeatherScreen: $actionEvent")

                    }

                    else -> {}
                }
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (uiState.isLoading) {
                LoadingIndicator("Loading data...")
            } else if (uiState.error != null) {
                ShowError(uiState.error) {
                    viewModel.onIntent(AppEvent.RefreshClicked)
                }
            } else if (uiState.aqi != null) {

                Text(
                    text = uiState.city.cityName,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Text(
                    text = "AQI",
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = uiState.aqi.getInfo(),
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                AirQualityComponent(uiState.aqi)

                Text(
                    text = "Data from city sensors (placeholder)",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(top = 16.dp),
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
                )
            }
        }
    }

}

@Preview
@Composable
fun AQIScreenPreview() {
    AQIScreen()
}