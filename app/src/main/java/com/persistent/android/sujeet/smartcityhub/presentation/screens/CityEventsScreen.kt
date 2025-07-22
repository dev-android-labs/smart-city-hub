package com.persistent.android.sujeet.smartcityhub.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.persistent.android.sujeet.smartcityhub.presentation.home.StatsUiState

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
@Composable
fun CityEventsScreen(uiState: StatsUiState) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "City Events",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        if (uiState.alerts.isEmpty()) {
            Text("No upcoming events.")
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                uiState.alerts.forEach { event ->
                    Text(
                        text = "• $event",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
        Text(
            text = "Data from city event management (placeholder)",
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
        )
    }
}