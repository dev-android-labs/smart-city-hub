package com.persistent.android.sujeet.smartcityhub.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.persistent.android.sujeet.smartcityhub.R
import com.persistent.android.sujeet.smartcityhub.domain.model.AirQuality
import com.persistent.android.sujeet.smartcityhub.domain.model.City
import com.persistent.android.sujeet.smartcityhub.domain.model.Component
import com.persistent.android.sujeet.smartcityhub.domain.model.Forecast
import com.persistent.android.sujeet.smartcityhub.domain.model.Weather
import com.persistent.android.sujeet.smartcityhub.utils.FORMAT
import com.persistent.android.sujeet.smartcityhub.utils.TimeUtil
import kotlinx.coroutines.launch
import java.util.Locale
import kotlin.math.roundToInt

/**
 * Created by SUJEET KUMAR on 7/20/2025
 */
@Composable
fun WeatherCard(weather: Weather) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = weather.cityName,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                AsyncImage(
                    model = weather.getIconImageUrl(),
                    contentDescription = weather.condition,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "${weather.temperature.roundToInt()}°C",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Text(
                text = weather.description.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                },
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(16.dp))
            WeatherDetailRow(label = "Feels like", value = "${weather.feelsLike.roundToInt()}°C")
            WeatherDetailRow(label = "Humidity", value = "${weather.humidity}%")
            WeatherDetailRow(label = "Wind Speed", value = "${weather.windSpeed} m/s")
            WeatherDetailRow(label = "Pressure", value = "${weather.pressure} hPa")
            WeatherDetailRow(
                label = "Min/Max Temp",
                value = "${weather.minTemp.roundToInt()}°C / ${weather.maxTemp.roundToInt()}°C"
            )
            WeatherDetailRow(
                label = "Last Updated",
                value = TimeUtil.format(weather.dataTimestamp, FORMAT.DEFAULT_TIMESTAMP)
            )
        }
    }
}

@Composable
fun WeatherDetailRow(label: String, value: String) {
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
fun ForecastListComponent(forecasts: List<Forecast>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(forecasts) { forecast ->
            ForecastItemCard(forecast = forecast)
        }
    }
}

@Composable
fun ForecastItemCard(forecast: Forecast) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = TimeUtil.format(forecast.timestamp, FORMAT.EEE_MMM_D),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = TimeUtil.format(forecast.timestamp, FORMAT.HH_MM),
                    fontSize = 14.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Row(verticalAlignment = Alignment.CenterVertically) {
                forecast.iconUrl?.let { url ->
                    AsyncImage(
                        model = url,
                        contentDescription = forecast.condition,
                        modifier = Modifier.size(40.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text = "${forecast.temperature.roundToInt()}°C",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "${forecast.minTemp.roundToInt()}°C / ${forecast.maxTemp.roundToInt()}°C",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Text(
                text = forecast.description.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                },
                fontSize = 14.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 8.dp),
                textAlign = TextAlign.End
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AQICard(aqi: AirQuality, city: City = City.BANGALORE) {

    val scope = rememberCoroutineScope()
    val itemOneTooltipState = rememberTooltipState()

    val color = when (aqi.aqi) {
        1 -> colorResource(R.color.green_500)
        2 -> colorResource(R.color.yellow_500)
        3 -> colorResource(R.color.orange_500)
        4 -> colorResource(R.color.red_500)
        5 -> colorResource(R.color.maroon_500)

        else -> {
            MaterialTheme.colorScheme.surface
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = city.cityName,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Air Quality",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                Modifier
                    .background(shape = RoundedCornerShape(48), color = color)
                    .padding(horizontal = 32.dp, vertical = 8.dp),
            ) {
                Text(
                    text = aqi.getLevel().level.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.getDefault()
                        ) else it.toString()
                    },
                    fontSize = 48.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = aqi.getLevel().description.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                },
                fontSize = 12.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(modifier = Modifier.height(16.dp))

            AirQualityComponent(aqi)

            Text(
                text = "Pollutant concentration in μg/m3",
                fontSize = 12.sp,
                modifier = Modifier.padding(top = 16.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.6f)
            )
        }
    }
}

@Preview
@Composable
fun WeatherCardPreview() {
    Column {

        WeatherCard(
            weather = Weather(
                cityName = "Bangalore",
                temperature = 27.0,
                feelsLike = 23.0,
                minTemp = 21.0,
                maxTemp = 31.0,
                pressure = 1049,
                humidity = 98,
                condition = "Cloudy",
                description = "Cloudy with a chance of meatballs",
                iconUrl = "",
                windSpeed = 22.9,
                sunrise = System.currentTimeMillis(),
                sunset = System.currentTimeMillis(),
                lat = 79.2,
                lon = 23.5,
                dataTimestamp = System.currentTimeMillis(),
                icon = "02"
            )
        )

        AQICard(
            aqi = AirQuality(
                aqi = 4,
                component = Component(),
            )
        )
    }
}