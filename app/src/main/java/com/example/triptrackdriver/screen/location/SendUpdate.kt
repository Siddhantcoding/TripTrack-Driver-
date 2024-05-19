package com.example.triptrackdriver.screen.location

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.Locale


@Composable
fun SendLocationDetailToFirebase(context: Context, latitude: Double, longitude: Double) {

    val auth = Firebase.auth
    val db = Firebase.firestore
    var driverAddress by rememberSaveable { mutableStateOf("") }

    val geocoder = Geocoder(context, Locale.getDefault())
    val address: Address?

    val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
    if (addresses != null) {
        if (addresses.isNotEmpty()) {
            address = addresses[0]
            driverAddress = address.toString() // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex
            var city = address.locality;
            var state = address.adminArea;
            var country = address.countryName;
            var postalCode = address.postalCode;
            var knownName = address.featureName; // Only if available else return NULL

            val user = auth.currentUser
            var cleanAddr = ""
            for(lines in address.getAddressLine(0)){
                cleanAddr += lines
            }
            user?.uid?.let {
                db.collection("DRIVER").document(it).set(
                    hashMapOf(
                        "user" to user.uid,
                        "latitude" to latitude,
                        "longitude" to longitude,
                        "address" to cleanAddr
                    )
                )
            }

        } else {
            driverAddress = "Location not found"
        }
    }
}