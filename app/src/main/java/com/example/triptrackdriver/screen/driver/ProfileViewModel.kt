package com.example.triptrackdriver.screen.driver

import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


data class Driver(
    val user: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val stops: List<StopLocation> = emptyList(),
    val address: String = "",
    val homeAddress: String = "",
    val isDriving: Boolean = false,
    val vehicleType: String = "Taxi",
    val phoneNumber: String = "",
    val aadhaarCardNumber: String = "",
    val name: String = "",
    val email: String = "",
)

data class StopLocation(
    val latitude: Double = 0.0, val longitude: Double = 0.0, val address: String = ""
)

enum class VehicleChoices {
    TAXI, E_RICKSHAW, BUS
}

enum class ProfileSaveState {
    IDLE, SAVING, SAVED, ERROR
}

enum class StopLocationSaveState {
    IDLE, SAVING, SAVED, ERROR
}

data class DriverState(
    val isDriving: Boolean = false, // is online
    val driver: Driver = Driver(),
    val vehicleType: VehicleChoices = VehicleChoices.TAXI,
    val phoneNumber: String = "",
    val aadhaarCardNumber: String = "",
    val name: String = "",
    val email: String = "",
    val homeAddress: String = "",
    val stops: List<StopLocation> = emptyList(),
    val stopLocLat: Double = 0.0,
    val stopLocLng: Double = 0.0,
    val stopLocAddress: String = "",
    val saveState: ProfileSaveState = ProfileSaveState.IDLE,
    val stopLocSaveState: StopLocationSaveState = StopLocationSaveState.IDLE
)

sealed class ProfileEvent {
    // driver actions
//    data class UpdateDriverProfile(val driver: Driver) : ProfileEvent()
    data class GetDriverProfile(val driver: Driver) : ProfileEvent()
    data class UpdateDriverLocation(
        val latitude: Double, val longitude: Double, val address: String
    ) : ProfileEvent()

    // status actions
    data class UpdateStatus(val isDriving: Boolean) : ProfileEvent()

    // stop location actions
    data class RemoveStopLocation(val stopLocation: StopLocation) : ProfileEvent()
    data class UpdateStopAddress(val address: String) : ProfileEvent()

    // form actions
    data class UpdateVehicleType(val vehicleType: VehicleChoices) : ProfileEvent()
    data class UpdatePhoneNumber(val phoneNumber: String) : ProfileEvent()
    data class UpdateAadhaarCardNumber(val aadhaarCardNumber: String) : ProfileEvent()
    data class UpdateName(val name: String) : ProfileEvent()
    data class UpdateEmail(val email: String) : ProfileEvent()
    data class UpdateAddress(val homeAddress: String) : ProfileEvent()
    data object SaveProfile : ProfileEvent()
    data object GetProfile : ProfileEvent()
    data object SaveStopLocation : ProfileEvent()
}


class ProfileViewModel(
    private val geocoder: Geocoder,
    private val auth: FirebaseAuth = Firebase.auth,
    private val db: FirebaseFirestore = Firebase.firestore
) : ViewModel() {
    private val _state = MutableStateFlow(DriverState())
    val state = _state.asStateFlow()

    init {
        // clear the state
        _state.value = DriverState()
        getProfile()
    }


    private fun updateStatus(isDriving: Boolean) {
        _state.value = _state.value.copy(isDriving = isDriving)
    }

    private fun addStopLocation(stopLocation: StopLocation) {
        // save it on firebase
        val uid = _state.value.driver.user
        if (uid.isNotEmpty()) {
            db.collection("DRIVER").document(uid).update(
                "stops", _state.value.driver.stops + stopLocation
            )
        }
    }

    private fun removeStopLocation(stopLocation: StopLocation) {
        // remove it from firebase
        val uid = _state.value.driver.user
        if (uid.isNotEmpty()) {
            db.collection("DRIVER").document(uid).update(
                "stops", _state.value.driver.stops - stopLocation
            )
        }
    }

    private fun getDriverProfile() {
        val user = auth.currentUser
        if (user != null) {
            db.collection("DRIVER").document(user.uid).get().addOnSuccessListener {
                val driver = it.toObject(Driver::class.java)
                if (driver != null) {
                    _state.value = _state.value.copy(driver = driver)
                }
            }
        }
    }

    private fun updateDriverLocation(latitude: Double, longitude: Double, address: String) {
        val user = auth.currentUser
        if (user != null) {
            db.collection("DRIVER").document(user.uid).update(
                "latitude", latitude, "longitude", longitude, "address", address
            )
        }
    }

    private fun saveProfile() {
        // update state
        _state.value = _state.value.copy(saveState = ProfileSaveState.SAVING)
        val user = auth.currentUser
        if (user != null) {
            val driver = Driver(
                user = user.uid,
                latitude = _state.value.driver.latitude,
                longitude = _state.value.driver.longitude,
                address = _state.value.driver.address,
                stops = _state.value.driver.stops,
                isDriving = _state.value.isDriving,
                vehicleType = _state.value.vehicleType.name,
                phoneNumber = _state.value.phoneNumber,
                aadhaarCardNumber = _state.value.aadhaarCardNumber,
                homeAddress = _state.value.homeAddress,
                name = _state.value.name,
                email = _state.value.email
            )
            db.collection("DRIVER").document(user.uid).set(driver).addOnSuccessListener {
                    _state.value = _state.value.copy(saveState = ProfileSaveState.SAVED)
                }.addOnFailureListener {
                    _state.value = _state.value.copy(saveState = ProfileSaveState.ERROR)
                }
        } else {
            _state.value = _state.value.copy(saveState = ProfileSaveState.ERROR)
        }
    }

    private fun getProfile() {
        val user = auth.currentUser
        if (user != null) {
            db.collection("DRIVER").document(user.uid).get().addOnSuccessListener {
                val driver = it.toObject(Driver::class.java)
                if (driver != null) {
                    // set all the values in the state
                    _state.update { state ->
                        state.copy(
                            driver = driver,
                            vehicleType = when (state.driver.vehicleType) {
                                "TAXI" -> VehicleChoices.TAXI
                                "E_RICKSHAW" -> VehicleChoices.E_RICKSHAW
                                "BUS" -> VehicleChoices.BUS
                                else -> VehicleChoices.TAXI
                            },
                            phoneNumber = driver.phoneNumber,
                            aadhaarCardNumber = driver.aadhaarCardNumber,
                            name = driver.name,
                            email = driver.email,
                            homeAddress = driver.homeAddress,
                            stops = driver.stops

                        )
                    }
                    Log.d("ProfileViewModel", "State: ${_state.value}")
                } else {
                    _state.value = _state.value.copy(driver = Driver(user = user.uid))
                }
            }
        }

    }

    private fun convertAddress() {
        val address = _state.value.stopLocAddress
        if (address.isNotEmpty()) {
            try {
                Log.d("ProfileViewModel", "Address: $address")
                val addressList: MutableList<Address>? = geocoder.getFromLocationName(address, 1)
                Log.d("ProfileViewModel", "AddressList: $addressList")
                if (addressList != null) {
                    if (addressList.isNotEmpty()) {
                        val lat: Double = addressList[0].latitude
                        val lng: Double = addressList[0].longitude
                        Log.d("ProfileViewModel", "Lat: $lat, Lng: $lng")
                        _state.value = _state.value.copy(stopLocLat = lat, stopLocLng = lng)
                        val user = auth.currentUser
                        if (user != null) {
                            // update stops in the driver collection
                            val stopLocation = StopLocation(lat, lng, address)
                            // get list of saved stops
                            val stops = _state.value.driver.stops
                            // add the new stop
                            _state.value = _state.value.copy(driver = _state.value.driver.copy(stops = stops + stopLocation))
                            // save the stop location
                            db.collection("DRIVER").document(user.uid).update(
                                "stops", _state.value.driver.stops
                            )
                            // clear the stop location address and lat/lng
                            _state.value = _state.value.copy(stopLocAddress = "")
                            _state.value = _state.value.copy(stopLocLat = 0.0)
                            _state.value = _state.value.copy(stopLocLng = 0.0)
                            _state.value = _state.value.copy(stopLocSaveState = StopLocationSaveState.SAVED)
                            _state.value = _state.value.copy(stopLocSaveState = StopLocationSaveState.IDLE)

                            // get the updated driver profile
                            getDriverProfile()
                        }
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            } // end if
        } // end convertAddress
    }


    private fun saveStopLocation() {
        convertAddress()

    }

    // events
    fun onEvent(event: ProfileEvent) {
        when (event) {
//            is ProfileEvent.UpdateDriverProfile -> updateDriverProfile(event.driver)
            is ProfileEvent.UpdateStatus -> updateStatus(event.isDriving)
            is ProfileEvent.RemoveStopLocation -> removeStopLocation(event.stopLocation)
            is ProfileEvent.GetDriverProfile -> getDriverProfile()
            is ProfileEvent.UpdateDriverLocation -> updateDriverLocation(
                event.latitude, event.longitude, event.address
            )

            is ProfileEvent.UpdateVehicleType -> _state.value =
                _state.value.copy(vehicleType = event.vehicleType)

            is ProfileEvent.UpdatePhoneNumber -> _state.value =
                _state.value.copy(phoneNumber = event.phoneNumber)

            is ProfileEvent.UpdateAadhaarCardNumber -> _state.value =
                _state.value.copy(aadhaarCardNumber = event.aadhaarCardNumber)

            is ProfileEvent.UpdateName -> _state.value = _state.value.copy(name = event.name)
            is ProfileEvent.UpdateEmail -> _state.value = _state.value.copy(email = event.email)
            is ProfileEvent.UpdateAddress -> _state.value =
                _state.value.copy(homeAddress = event.homeAddress)

            is ProfileEvent.UpdateStopAddress -> _state.value =
                _state.value.copy(stopLocAddress = event.address)

            is ProfileEvent.SaveProfile -> saveProfile()
            is ProfileEvent.GetProfile -> getProfile()
            ProfileEvent.SaveStopLocation -> saveStopLocation()
        }
    }

    class Factory(private val geocoder: Geocoder) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST") return ProfileViewModel(geocoder) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}