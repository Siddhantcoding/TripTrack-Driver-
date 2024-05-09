package com.example.triptrackdriver.screen.driverstatus

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun DriverStatusScreen(
    onClick: () -> Unit = {}
) {
    var isOnline by remember { mutableStateOf(false) }
    Box(

        modifier = Modifier
            .fillMaxSize()
            .background(if (isOnline) Color.Green else Color.Green.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {

        Text(text = if (isOnline) "Online" else "Offline")

        Button(onClick = {
            isOnline = !isOnline
            onClick()
        }) {
            Text(text = if (isOnline) "Go Offline" else "Go Online")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DriverStatusScreenPreview() {
    DriverStatusScreen()
}

