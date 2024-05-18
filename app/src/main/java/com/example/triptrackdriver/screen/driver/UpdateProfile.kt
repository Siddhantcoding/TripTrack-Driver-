package com.example.triptrackdriver.screen.driver

import androidx.compose.ui.Modifier


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.triptrackdriver.ui.theme.TripTrackDriverTheme

@Composable
fun UpdateProfileForm(
    onFormSubmit: (String, String, String, String) -> Unit
) {
    val vehicleType = remember { mutableStateOf("") }
    val usualRoutes = remember { mutableStateOf("") }
    val phoneNumber = remember { mutableStateOf("") }
    val aadharCardNumber = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp)

    ){
        Text(text ="Update Profile", style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center)
        TextField(
            value = vehicleType.value,
            onValueChange = { vehicleType.value = it },
            label = { Text("Vehicle Type") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = usualRoutes.value,
            onValueChange = { usualRoutes.value = it },
            label = { Text("Usual Routes") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = phoneNumber.value,
            onValueChange = { phoneNumber.value = it },
            label = { Text("Phone Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = aadharCardNumber.value,
            onValueChange = { aadharCardNumber.value = it },
            label = { Text("Aadhar Card Number") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            onFormSubmit(
                vehicleType.value,
                usualRoutes.value,
                phoneNumber.value,
                aadharCardNumber.value
            )
        }) {
            Text("Submit")
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun UpdateProfilePreview() {
    TripTrackDriverTheme {
        UpdateProfileForm { _, _, _, _ -> }
    }

}