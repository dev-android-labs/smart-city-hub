package com.persistent.android.sujeet.smartcityhub.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.persistent.android.sujeet.smartcityhub.domain.model.City

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */
@Composable
fun CitySelectionDialog(
    cities: List<City>,
    onCitySelected: (City) -> Unit,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Select Your City") },
        text = {
            LazyColumn(
                modifier = Modifier.heightIn(max = 300.dp) // Limit height for scrollable content
            ) {
                items(cities) { city ->
                    ListItem(
                        modifier = Modifier.clickable {
                            onCitySelected(city)
                        },
                        headlineContent = { Text(city.toString()) },
                        leadingContent = {
                            Icon(Icons.Default.LocationOn, contentDescription = null)
                        }
                    )
                    Spacer(Modifier.height(1.dp))
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Preview
@Composable
fun CitySelectionDialogPreview() {
    CitySelectionDialog(
        cities = City.entries,
        onCitySelected = {},
        onDismiss = {}
    )
}