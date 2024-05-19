package com.example.triptrackdriver.screen.driver

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.triptrackdriver.R
import com.example.triptrackdriver.ui.theme.TripTrackDriverTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StopLocationScreen(
    state: DriverState, onEvent: (ProfileEvent) -> Unit, onBack: () -> Unit
) {
    val scaffoldState = rememberBottomSheetScaffoldState()
    val scope = rememberCoroutineScope()


    BottomSheetScaffold(scaffoldState = scaffoldState,
        sheetPeekHeight = 80.dp, // BottomSheet's height when it's collapsed
        sheetShadowElevation = 12.dp,
        sheetContent = {
            AddStopLocationForm(state, onEvent)
        }) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Image(
                painter = painterResource(id = R.drawable.driver_wall),
                contentDescription = "Location Icon",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Column {
                StopLocationList(state = state, onEvent = onEvent)
                Spacer(modifier = Modifier.height(8.dp))
                Button(onClick = {
                    scope.launch { scaffoldState.bottomSheetState.expand() }
                }) {
                    Text(text = "Add Stop Location")
                }
            }
        }
    }
}

@Composable
fun StopLocationList(state: DriverState, onEvent: (ProfileEvent) -> Unit) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.driver.stops) { stopLocation ->
            StopCard(stopLocation = stopLocation, onEvent = onEvent)
        }
    }

}

@Composable
fun StopCard(stopLocation: StopLocation, onEvent: (ProfileEvent) -> Unit) {
    Card(
        onClick = {}, modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(stopLocation.address, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.fillMaxWidth())
            // launch the map intent
        }
    }
}

@Composable
fun AddStopLocationForm(state: DriverState, onEvent: (ProfileEvent) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // enter address of the stop location
        Text("Enter address of the stop location")
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = state.stopLocAddress ?: "",
            onValueChange = { onEvent(ProfileEvent.UpdateStopAddress(it)) },
            label = { Text(text = "Address") },
        )
        // save the stop location
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { onEvent(ProfileEvent.SaveStopLocation) }) {
            Text("Save Stop Location")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun UsualRoutesPreview() {
    TripTrackDriverTheme {
        StopLocationScreen(state = DriverState(
            driver = Driver(
                stops = listOf(
                    StopLocation(0.3, 0.3, "123 Main St"), StopLocation(0.1, 0.2, "456 Elm St")

                )
            ), stopLocAddress = "123 Main St"
        ), onEvent = {}, onBack = {})
    }
    
}