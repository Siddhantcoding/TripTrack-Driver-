package com.example.triptrackdriver.screen.driver

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.triptrackdriver.R
import com.example.triptrackdriver.ui.theme.TripTrackDriverTheme

@Composable
fun DriverStatusScreen(
    onGoOnline: () -> Unit = {},
    onUpdateProfile: () -> Unit = {},
    onStopLocationSelection: () -> Unit = {}
) {
    var isOnline by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isOnline) Color.Green else Color.Green.copy(alpha = 0.5f)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.driver_wall),
            contentDescription = "",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier.width(150.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                isOnline = !isOnline
                    onGoOnline()
            }) {
                Text(text = if (isOnline) "Go Offline" else "Go Online")
            }

            // Add this button
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onUpdateProfile
            ) {
                Text(text = "Update Profile")
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onStopLocationSelection
            ) {
                Text(text = "Locations")
            }
        }
    }
}

@Preview
@Composable
private fun DriverStatusScreenPreview() {
    TripTrackDriverTheme {
        DriverStatusScreen()
    }
}
