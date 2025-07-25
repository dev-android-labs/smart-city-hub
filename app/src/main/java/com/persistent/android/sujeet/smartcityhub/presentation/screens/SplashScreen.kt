package com.persistent.android.sujeet.smartcityhub.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.persistent.android.sujeet.smartcityhub.R
import com.persistent.android.sujeet.smartcityhub.presentation.components.SmartCityComponent
import com.persistent.android.sujeet.smartcityhub.presentation.routes.Routes
import kotlinx.coroutines.delay

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */

@Composable
fun SplashScreen(navController: NavController) {

    LaunchedEffect(Unit) {

        delay(5000)
        navController.navigate(Routes.HomeScreen.name)
    }

    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        SmartCityComponent()

        Text(
            text = stringResource(R.string.app_name),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.headlineMedium
        )
    }


}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen(rememberNavController())
}