package com.persistent.android.sujeet.smartcityhub.presentation.alerts

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

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
@Composable
fun AlertsScreen(uiState: AlertUiState){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "City Alerts",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
//        if (viewModel.alerts.isEmpty()) {
        if (false) {
            Text("No active alerts at the moment.")
        } else {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                uiState.alerts.forEach { alert ->
                    Text(
                        text = "• $alert",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
        Text(
            text = "Data from city departments (placeholder)",
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 16.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
        )
    }
}