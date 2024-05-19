package com.example.triptrackdriver.screen.driver


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.triptrackdriver.ui.theme.TripTrackDriverTheme

@Composable
fun ProfileScreen(
    state: DriverState = DriverState(),
    onEvent: (ProfileEvent) -> Unit,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Name of the driver
        Column(
            modifier = Modifier
                .weight(1f)
                .width(400.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = state.name,
                onValueChange = { onEvent(ProfileEvent.UpdateName(it)) },
                label = { Text(text = "Your Name") },
            )
            // Email of the driver
            OutlinedTextField(
                value = state.email,
                onValueChange = { onEvent(ProfileEvent.UpdateEmail(it)) },
                label = { Text(text = "Your Email") },
            )
            // Phone number of the driver
            OutlinedTextField(
                value = state.phoneNumber,
                onValueChange = { onEvent(ProfileEvent.UpdatePhoneNumber(it)) },
                label = { Text(text = "Your Phone Number") },
            )
            // Aadhaar card number of the driver
            OutlinedTextField(
                value = state.aadhaarCardNumber,
                onValueChange = { onEvent(ProfileEvent.UpdateAadhaarCardNumber(it)) },
                label = { Text(text = "Your Aadhaar Card Number") },
            )
            // Address of the driver
            OutlinedTextField(
                value = state.homeAddress,
                onValueChange = { onEvent(ProfileEvent.UpdateAddress(it)) },
                label = { Text(text = "Home Address") },
            )
            // Vehicle type of the driver
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Taxi")
                    RadioButton(
                        selected = state.vehicleType == VehicleChoices.TAXI,
                        onClick = { onEvent(ProfileEvent.UpdateVehicleType(VehicleChoices.TAXI)) })
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "E-Rickshaw")
                    RadioButton(
                        selected = state.vehicleType == VehicleChoices.E_RICKSHAW,
                        onClick = { onEvent(ProfileEvent.UpdateVehicleType(VehicleChoices.E_RICKSHAW)) })
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Bus")
                    RadioButton(
                        selected = state.vehicleType == VehicleChoices.BUS,
                        onClick = { onEvent(ProfileEvent.UpdateVehicleType(VehicleChoices.BUS)) })
                }
            }
        }


        // Save button
        Button(onClick = { onEvent(ProfileEvent.SaveProfile) }) {
            Text(text = "Save/Update profile")
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun UpdateProfilePreview() {
    TripTrackDriverTheme {
        ProfileScreen(
            state = DriverState(),
            onEvent = {},
            onBack = {}
        )
    }
}