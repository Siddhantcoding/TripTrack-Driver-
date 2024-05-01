data class RegisterState(
    val email: String = "",
    val password: String = "",
    val username: String = "",
    val confirmPassword: String = "",
    val phoneNumber: Long = 0,
    var isLoading: Boolean = false,
    var error: String = "",
    var isRegisterSuccess: Boolean = false,
) {
}