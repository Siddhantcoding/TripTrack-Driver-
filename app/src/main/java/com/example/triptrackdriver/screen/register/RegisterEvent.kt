sealed class RegisterEvent {
    data class Register(val name: String, val email: String, val username: String, val password: String, val confirmPassword: String) : RegisterEvent()
    data object NavigateBack : RegisterEvent()
}