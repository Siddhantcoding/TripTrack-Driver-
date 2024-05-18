package com.example.triptrackdriver.screen.login

data class LoginState(
    var email: String = "",
    var password: String = "",
    val VehicleType: String = "",
    val isLoading: Boolean = false,
    val error: String = "",
    val isLoginSuccess: Boolean = false,
)