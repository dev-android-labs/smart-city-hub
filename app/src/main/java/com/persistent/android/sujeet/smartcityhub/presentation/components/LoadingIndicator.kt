package com.persistent.android.sujeet.smartcityhub.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
fun LoadingIndicator(loadingMessage: String) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        CircularProgressIndicator(modifier = Modifier.padding(32.dp))
        Text(
            loadingMessage,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}