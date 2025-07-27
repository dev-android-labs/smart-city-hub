package com.persistent.android.sujeet.smartcityhub.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.persistent.android.sujeet.smartcityhub.R
import com.persistent.android.sujeet.smartcityhub.domain.model.AirQuality
import com.persistent.android.sujeet.smartcityhub.domain.model.Service
import com.persistent.android.sujeet.smartcityhub.domain.model.Stats
import com.persistent.android.sujeet.smartcityhub.presentation.home.StatsUiState
import kotlin.math.roundToInt

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
@Composable
fun Components() {

    Column(Modifier.fillMaxSize()) {

        QuickStatsComponent(StatsUiState())

        FeatureButton("Aqi") {

        }
    }
}

@Composable
fun HomeHeader(uiState: StatsUiState, onClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Quick Stats",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            modifier = Modifier.clickable(onClick = onClick),
            text = uiState.city.cityName,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun QuickStatsComponent(uiState: StatsUiState, onClick: (Stats) -> Unit = {}) {

    Row(
        Modifier
            .height(80.dp)
            .background(
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colorScheme.primaryContainer
            )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {

        Box(
            Modifier
                .clickable(onClick = { onClick(Stats.WEATHER) })
                .weight(1f)
                .fillMaxHeight()
                .padding(8.dp)
                .background(
                    MaterialTheme.colorScheme.primary,
                    shape = MaterialTheme.shapes.medium
                ),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    uiState.weather?.getIconImageUrl()?.let { url ->
                        AsyncImage(
                            model = url,
                            contentDescription = uiState.weather.condition,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                    Text(
                        text = "${uiState.weather?.temperature?.roundToInt()}°C",
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }

                Spacer(Modifier.height(8.dp))
                Text(
                    uiState.weather?.condition ?: "na",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }

        Box(
            Modifier
                .clickable(onClick = { onClick(Stats.AQI) })
                .weight(1f)
                .fillMaxHeight()
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    "AQI",
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(Modifier.height(8.dp))
                uiState.aqi?.let {
                    Text(
                        text = it.getInfo(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }

        Box(
            Modifier
                .clickable(onClick = { onClick(Stats.TRAFFIC) })
                .weight(1f)
                .fillMaxHeight()
                .padding(8.dp)
                .background(MaterialTheme.colorScheme.primary, shape = MaterialTheme.shapes.medium),
            contentAlignment = Alignment.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_drive_eta_24),
                    contentDescription = "Traffic Icon",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    "Traffic",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun ServicesComponent(onServiceItemClick: (Service) -> Unit) {

    Column(
        Modifier.background(
            shape = RoundedCornerShape(4.dp),
            color = MaterialTheme.colorScheme.primaryContainer
        )
    ) {

        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(
                        MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                ServiceItem(
                    Service.PUBLIC_TRANSPORT,
                    onClick = onServiceItemClick
                )
            }

            Box(
                Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(
                        MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                ServiceItem(
                    Service.EMERGENCY_SERVICES,
                    onClick = onServiceItemClick
                )
            }
        }

        Row(
            Modifier
                .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(
                        MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                ServiceItem(
                    Service.WASTE_MANAGEMENT,
                    onClick = onServiceItemClick
                )
            }

            Box(
                Modifier
                    .weight(1f)
                    .padding(8.dp)
                    .background(
                        MaterialTheme.colorScheme.surface,
                        shape = MaterialTheme.shapes.medium
                    ),
                contentAlignment = Alignment.Center
            ) {
                ServiceItem(
                    Service.CITY_EVENTS,
                    onClick = onServiceItemClick
                )
            }
        }

    }
}

@Composable
fun ServiceItem(service: Service, onClick: (Service) -> Unit) {

    Row(
        modifier = Modifier
            .padding(8.dp)
            .height(64.dp)
            .clickable(
                onClick = { onClick(service) }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Icon(
            painterResource(service.resId),
            service.serviceName,
            Modifier
                .size(42.dp)
                .padding(8.dp)

        )
        Text(
            service.serviceName,
            maxLines = 2,
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
fun AlertItem(
    text: String,
    subText: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null, // Optional leading icon
    showDivider: Boolean = true, // Option to show/hide a divider
    onClick: () -> Unit,
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
                modifier = Modifier.weight(
                    1f,
                    fill = false
                ) // Allow text to take only necessary width
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
                Column {


                    Text(
                        text = text,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge, // Adjust text style
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,

                        )
                    Text(
                        text = subText,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurface,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
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


@Composable
fun FeatureButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(vertical = 8.dp)
    ) {
        Text(text = text, fontSize = 18.sp)
    }
}

@Composable
fun AirQualityComponent(aqi: AirQuality) {
    Column(
        Modifier
            .fillMaxWidth()
    ) {
        KeyValueRow("CO", "${aqi.component.co}")
        KeyValueRow("NO", "${aqi.component.no}")
        KeyValueRow("NO2", "${aqi.component.no2}")
        KeyValueRow("O3", "${aqi.component.o3}")
        KeyValueRow("SO2", "${aqi.component.so2}")
        KeyValueRow("PM2.5", "${aqi.component.pm2_5}")
        KeyValueRow("PM10", "${aqi.component.pm10}")
        KeyValueRow("NH3", "${aqi.component.nh3}")
    }
}


@Composable
fun KeyValueRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun SmartCityComponent() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.splash_smart_city))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever // Or specify a number of iterations
    )

    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier.fillMaxWidth() // Adjust modifier as needed
    )
}


@Preview
@Composable
fun ComponentsPreview() {
    Components()
}