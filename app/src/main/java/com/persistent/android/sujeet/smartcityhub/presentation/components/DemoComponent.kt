package com.persistent.android.sujeet.smartcityhub.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */
@Composable
fun TextWithNextIconRow(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null, // Optional leading icon
    showDivider: Boolean = true // Option to show/hide a divider
) {
    Column { // Use Column to stack Row and optional Divider
        Row(
            modifier = modifier
                .fillMaxWidth()
                .clickable(onClick = onClick) // Make the entire row clickable
                .padding(vertical = 12.dp, horizontal = 16.dp), // Add internal padding
            horizontalArrangement = Arrangement.SpaceBetween, // Pushes text and icon to opposite ends
            verticalAlignment = Alignment.CenterVertically // Vertically centers both text and icon
        ) {
            Row( // Inner Row for text and optional leading icon
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f, fill = false) // Allow text to take only necessary width
            ) {
                leadingIcon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = null, // Content description for accessibility if needed
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.size(24.dp) // Adjust icon size
                    )
                    Spacer(modifier = Modifier.width(16.dp)) // Space between icon and text
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyLarge, // Adjust text style
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight, // The "next" icon
                contentDescription = "Navigate to next screen", // For accessibility
                tint = MaterialTheme.colorScheme.onSurfaceVariant, // Adjust icon color
                modifier = Modifier.size(24.dp) // Adjust icon size
            )
        }

        if (showDivider) {
            Divider(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp), // Padding for the divider
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTextWithNextIconRow() {
    Column {
        TextWithNextIconRow(
            text = "My Profile",
            onClick = { /* Navigate to profile */ },
            leadingIcon = Icons.Filled.Info // Example with a leading icon
        )
        TextWithNextIconRow(
            text = "SettingsSettingsSettingsSettingsSettingsSettings",
            onClick = { /* Navigate to settings */ }
        )
        TextWithNextIconRow(
            text = "About Us",
            onClick = { /* Navigate to about us */ },
            showDivider = false // Example without a divider
        )
    }
}