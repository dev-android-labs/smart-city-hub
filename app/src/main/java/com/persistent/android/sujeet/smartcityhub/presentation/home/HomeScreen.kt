package com.persistent.android.sujeet.smartcityhub.presentation.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.persistent.android.sujeet.smartcityhub.R
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.model.Stats
import com.persistent.android.sujeet.smartcityhub.presentation.components.AlertItem
import com.persistent.android.sujeet.smartcityhub.presentation.components.CitySelectionDialog
import com.persistent.android.sujeet.smartcityhub.presentation.components.HomeHeader
import com.persistent.android.sujeet.smartcityhub.presentation.components.QuickStatsComponent
import com.persistent.android.sujeet.smartcityhub.presentation.components.ServicesComponent
import com.persistent.android.sujeet.smartcityhub.presentation.routes.Routes
import com.persistent.android.sujeet.smartcityhub.utils.TimeUtil

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: StatsUiState = StatsUiState(),
    viewModel: StatsViewModel = hiltViewModel(),
    navController: NavController = rememberNavController(),
) {

    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.viewEffects.collect { viewEffect ->

            when (viewEffect) {
                is ViewEffects.NavigateToWeatherScreen -> {
                    navController.navigate(Routes.WeatherScreen.name)
                }

                is ViewEffects.NavigateToAQIScreen -> {
                    navController.navigate(Routes.AQIScreen.name)
                }

                is ViewEffects.NavigateToAlertScreen -> {
                    navController.navigate(Routes.AlertScreen.name)
                }

                is ViewEffects.ShowToast -> {
                    Toast.makeText(context, viewEffect.message, Toast.LENGTH_SHORT).show()
                }

                else -> {

                }
            }

        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                ),
                actions = {
                    IconButton(onClick = {
                        viewModel.onIntent(StatsEvent.SettingClicked)
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = "Settings",
                            tint = MaterialTheme.colorScheme.primaryContainer
                        )
                    }
                    // You can add more action icons here if needed
                    IconButton(onClick = {
                        viewModel.onIntent(StatsEvent.Refresh(uiState.city))
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Refresh,
                            contentDescription = "Refresh Data",
                            tint = MaterialTheme.colorScheme.primaryContainer
                        )
                    }
                },
                title = { Text(stringResource(R.string.app_name), maxLines = 1) },
            )
        },
    ) { innerPadding ->

        Box(
            Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Column(
                Modifier
                    .padding(8.dp)

                    .fillMaxSize()
                    .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                if (uiState.showCitySelectionDialog) {
                    CitySelectionDialog(
                        cities = City.entries,
                        onCitySelected = {
                            viewModel.onIntent(StatsEvent.CitySelected(it))
                        },
                        onDismiss = {
                            viewModel.onIntent(StatsEvent.CityDialogDismiss)
                        }
                    )
                }

                HomeHeader(uiState) {
                    viewModel.onIntent(StatsEvent.SettingClicked)
                }

                QuickStatsComponent(uiState) { stats ->
                    when (stats) {
                        Stats.WEATHER -> viewModel.onIntent(StatsEvent.WeatherClicked)
                        Stats.AQI -> viewModel.onIntent(StatsEvent.AqiClicked)
                        Stats.TRAFFIC -> viewModel.onIntent(StatsEvent.TrafficClicked)
                    }
                }

                ServicesComponent(onServiceItemClick = { service ->
                    viewModel.onIntent(StatsEvent.ServiceClicked(service))
                })


                Text(
                    text = "City Alerts",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.ExtraBold,
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Column(
                    Modifier.background(
                        shape = RoundedCornerShape(4.dp),
                        color = MaterialTheme.colorScheme.primaryContainer
                    ),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    uiState.alerts.forEach {
                        AlertItem(it.title, it.description) {
                            viewModel.onIntent(StatsEvent.AlertClicked(it))
                        }
                    }
                }

                uiState.weather?.let { Text(text = "Last Update ${TimeUtil.formatTime(it.dataTimestamp)}") }

            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(
        uiState = StatsUiState(),
        navController = rememberNavController(),
        viewModel = hiltViewModel()
    )
}