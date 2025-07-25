package com.persistent.android.sujeet.smartcityhub.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.persistent.android.sujeet.smartcityhub.R
import com.persistent.android.sujeet.smartcityhub.presentation.routes.AppEvent

/**
 * Created by SUJEET KUMAR on 7/26/2025
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarTop(
    title: String = stringResource(id = R.string.app_name),
    backEnabled: Boolean = false,
    settingsEnabled: Boolean = false,
    refreshEnabled: Boolean = false,
    onEvent: (AppEvent) -> Unit,
) {

    CenterAlignedTopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        navigationIcon = {
            if (backEnabled) {
                Button(onClick = {
                    onEvent(AppEvent.BackClicked)
                }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                }
            }
        },
        actions = {
            if (settingsEnabled) {
                IconButton(
                    onClick =
                        { onEvent(AppEvent.SettingClicked) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Settings,
                        contentDescription = "Settings",
                        tint = MaterialTheme.colorScheme.primaryContainer
                    )
                }
            }

            // You can add more action icons here if needed
            if (refreshEnabled) {
                IconButton(onClick = {
                    onEvent(AppEvent.RefreshClicked)
                }) {
                    Icon(
                        imageVector = Icons.Filled.Refresh,
                        contentDescription = "Refresh Data",
                        tint = MaterialTheme.colorScheme.primaryContainer
                    )
                }
            }
        }
    )
}