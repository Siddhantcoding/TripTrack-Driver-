import androidx.lifecycle.ViewModel
import com.example.triptrackdriver.screen.register.RegisterEvent
import com.example.triptrackdriver.service.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class RegisterViewModel(
    private val authService: AuthService = AuthService(),
) : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state = _state.asStateFlow()
    fun onEvent(event: RegisterEvent) {
        when (event) {
            RegisterEvent.OnSaveDriver -> {
                try {
                    authService.register(_state)
                } catch (e: Exception) {
                    _state.update {
                        it.copy(error = e.message ?: "Some error occurred")
                    }
                }
            }

            is RegisterEvent.SetConfirmPassword -> {
                _state.update { it.copy(confirmPassword = event.confirmPassword) }
            }

            is RegisterEvent.SetEmail -> {
                _state.update { it.copy(email = event.email) }
            }

            is RegisterEvent.SetPassword -> {
                _state.update { it.copy(password = event.password) }
            }

            is RegisterEvent.SetUsername -> {
                _state.update { it.copy(username = event.username) }
            }

            RegisterEvent.ClearError -> {
                _state.update { state ->
                    state.copy(error = "")
                }
            }

            else -> {}
        }
    }
}