package com.example.triptrackdriver.screen.driver

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.triptrackdriver.ui.theme.TripTrackDriverTheme


@Composable
fun UsualRoutes(
    onFormSubmit: (String, List<String>) -> Unit
) {
    val startDestination = remember { mutableStateOf("") }
    val stop  = remember { mutableStateOf("") }
    val stops = remember { mutableStateListOf<String>() }

    Column {
        TextField(
            value = startDestination.value,
            onValueChange = { startDestination.value = it },
            label = { Text("Start Destination") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = stop.value,
            onValueChange = { stop.value = it },
            label = { Text("Stops") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            onFormSubmit(startDestination.value, stops)
        }) {
            Text("Submit")
        }
    }
}


@Preview
@Composable
private fun UsualRoutesPreview() {
    TripTrackDriverTheme {
        UsualRoutes(onFormSubmit = { _, _ -> })
    }
    
}