package com.example.triptrackdriver.screen.login


sealed class LoginEvent {
    data object OnLogin : LoginEvent()
    data class SetEmail(val email: String) : LoginEvent()
    data class SetPassword(val password: String) : LoginEvent()
    data object ClearError : LoginEvent()
}