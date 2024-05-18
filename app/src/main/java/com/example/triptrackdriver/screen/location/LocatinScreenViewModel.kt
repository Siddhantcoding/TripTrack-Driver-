package com.example.triptrackdriver.screen.location

import android.content.Context
import android.location.Address
import android.location.Geocoder
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Locale

data class Tracker(
    val user: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val address: String = "",
)

class LocationScreenViewModel(
    private val auth: FirebaseAuth = Firebase.auth,
    private val db: FirebaseFirestore = Firebase.firestore
) : ViewModel() {
    private val _state = MutableStateFlow(Tracker())
    val state = _state.asStateFlow()

    fun updateLocation(latitude: Double, longitude: Double) {
        viewModelScope.launch {
            val user = auth.currentUser
            user?.uid?.let {
                db.collection("DRIVER").document(it).set(
                    Tracker(
                        user = user.uid,
                        latitude = latitude,
                        longitude = longitude,
                        address = "",
                    )
                )
            }
        }
    }

    fun updateAddress(
        context: Context,
        latitude: Double,
        longitude: Double,
    ) {
        viewModelScope.launch {
            val geocoder = Geocoder(context, Locale.getDefault())
            val address: Address?

            val addresses: List<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses != null) {
                if (addresses.isNotEmpty()) {
                    address = addresses[0]
                    address.toString() // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex
                    var city = address.locality;
                    var state = address.adminArea;
                    var country = address.countryName;
                    var postalCode = address.postalCode;
                    var knownName = address.featureName; // Only if available else return NULL


                } else {

                }
            }
        }
    }

    companion object {
        const val COLL_LOCATION = "Location"


    }
}