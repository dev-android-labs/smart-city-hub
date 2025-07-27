package com.persistent.android.sujeet.smartcityhub.presentation.aqi

import android.widget.Toast
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.presentation.components.AQICard
import com.persistent.android.sujeet.smartcityhub.presentation.components.AppBarTop
import com.persistent.android.sujeet.smartcityhub.presentation.components.CitySelectionDialog
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

                    is AppEvent.ActionRefreshClicked -> {
                        viewModel.onIntent(AppEvent.ActionRefreshClicked)
                    }

                    is AppEvent.ActionSettingClicked -> {
                        viewModel.onIntent(AppEvent.ActionSettingClicked)

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
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                if (uiState.showCitySelectionDialog) {
                    CitySelectionDialog(
                        cities = City.entries,
                        onCitySelected = {
                            viewModel.onIntent(AppEvent.CityChanged(it))
                        },
                        onDismiss = {
                            viewModel.onIntent(AppEvent.CityDialogDismiss)
                        }
                    )
                }

                if (uiState.isLoading) {
                    LoadingIndicator("Loading data...")
                } else if (uiState.error != null) {
                    ShowError(uiState.error) {
                        viewModel.onIntent(AppEvent.ActionRefreshClicked)
                    }
                } else if (uiState.aqi != null) {

                    AQICard(uiState.aqi, uiState.city)

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

}

@Preview
@Composable
fun AQIScreenPreview() {
    AQIScreen()
}