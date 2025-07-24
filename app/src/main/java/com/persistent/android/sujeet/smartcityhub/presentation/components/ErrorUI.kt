package com.persistent.android.sujeet.smartcityhub.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * Created by SUJEET KUMAR on 7/24/2025
 */

@Composable
fun ShowError(error: String, onRetryClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Error: $error",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = onRetryClick
        ) {
            Text("Retry")
        }
    }
}

@Composable
fun ShowError(error: String, buttonText: String, onButtonClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Error: $error",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
        Button(
            onClick = onButtonClick
        ) {
            Text(buttonText)
        }
    }
}

@Composable
fun ShowError(error: Exception, buttonText: String, onButtonClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Error: $error",
            color = MaterialTheme.colorScheme.error,
            modifier = Modifier.padding(16.dp)
        )
        Button(onClick = {
            onButtonClick
        }) {
            Text(buttonText)
        }
    }
}

