package com.persistent.android.sujeet.smartcityhub.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.persistent.android.sujeet.smartcityhub.R

/**
 * Created by SUJEET KUMAR on 7/24/2025
 */

@Composable
fun LoadingIndicator(loadingMessage: String) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AnimatedLoadingIndicator()
        Text(
            loadingMessage,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

@Composable
fun LoadingIndicator() {
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedLoadingIndicator()
    }
}

@Composable
fun ScreenLoadingIndicator() {
    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color.Gray.copy(alpha = 0.3f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AnimatedLoadingIndicator()
    }
}

@Composable
fun AnimatedLoadingIndicator() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.loading_animation))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever // Or specify a number of iterations
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier
            .fillMaxWidth()
            .padding(32.dp) // Adjust modifier as needed
    )
}

@Preview()
@Composable
fun LoadingIndicatorPreview() {
    Column(Modifier.fillMaxWidth()) {
        LoadingIndicator()
        LoadingIndicator("Loading...")
    }
}