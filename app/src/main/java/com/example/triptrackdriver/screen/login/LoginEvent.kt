package com.example.triptrackdriver.screen.login

sealed class LoginEvent {
    data class Login(val email: String, val password: String) : LoginEvent()
}