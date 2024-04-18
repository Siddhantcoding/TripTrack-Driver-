package com.example.triptrackdriver.screen.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _state = MutableStateFlow(LoginState())
    val state: StateFlow<LoginState> get() = _state

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.Login -> login(event.email, event.password)
        }
    }

    private fun login(email: String, password: String) {
        viewModelScope.launch {
            _state.value = LoginState(isLoading = true)

            // TODO: Implement the logic for checking if the entered email and password match the ones saved during registration.
            // This is just a placeholder. Replace it with your actual logic.
            val isLoginSuccessful = email == "test@example.com" && password == "password"

            if (isLoginSuccessful) {
                _state.value = LoginState(isLoggedIn = true)
            } else {
                _state.value = LoginState(error = "Invalid email or password")
            }
        }
    }
}