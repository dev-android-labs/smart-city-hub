package com.persistent.android.sujeet.smartcityhub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.persistent.android.sujeet.smartcityhub.presentation.routes.AppNavigation
import com.persistent.android.sujeet.smartcityhub.ui.theme.SmartCityHubTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            SmartCityHubTheme {
                AppNavigation()
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SmartCityHubTheme {
        AppNavigation()
    }
}