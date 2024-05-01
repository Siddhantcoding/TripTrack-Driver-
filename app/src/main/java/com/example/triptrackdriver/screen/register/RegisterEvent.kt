package com.example.triptrackdriver.screen.register

sealed class RegisterEvent {
    data class SetUsername(val username: String) : RegisterEvent()
    data class SetEmail(val email: String) : RegisterEvent()
    data class SetPassword(val password: String) : RegisterEvent()
    data class SetConfirmPassword(val confirmPassword: String) : RegisterEvent()
    data class SetPhoneNumber(val phoneNumber: Long) : RegisterEvent()
    data object OnSaveDriver : RegisterEvent()
    data object ClearError: RegisterEvent()
}