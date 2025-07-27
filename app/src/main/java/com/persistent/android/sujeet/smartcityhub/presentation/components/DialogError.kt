package com.persistent.android.sujeet.smartcityhub.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by SUJEET KUMAR on 7/21/2025
 */
@Composable
fun DialogError(
    title: String = "Error...!",
    message: String,
    onDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = title,
            )
        },
        text = { Text(text = message) },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = "OK")
            }
        }
    )
}

@Preview
@Composable
fun DialogErrorPreview() {
    DialogError(
        title = "Error",
        message = "Something went wrong",
        onDismiss = {}
    )
}