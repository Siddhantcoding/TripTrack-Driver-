package com.example.triptrackdriver.screen.location

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.triptrackdriver.service.AuthService
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LocationScreenViewModel(
    private val authService: AuthService = AuthService()
) : ViewModel() {
    val auth = Firebase.auth
    val db = Firebase.firestore
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
}