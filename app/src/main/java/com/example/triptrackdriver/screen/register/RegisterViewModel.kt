import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RegisterViewModel : ViewModel() {
    private val _state = MutableStateFlow(RegisterState())
    val state : StateFlow<RegisterState> get() = _state
    fun onEvent(event: RegisterEvent) {
        if (event is RegisterEvent.Register) register(event.name, event.email, event.username, event.password, event.confirmPassword)
    }

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance()

    fun register(name: String, email: String, username: String, password: String, confirmPassword: String) {
        if (password != confirmPassword) {
            // Handle password mismatch
            return
        }

        viewModelScope.launch {
            try {
                val result = auth.createUserWithEmailAndPassword(email, password).await()
                val user = hashMapOf(
                    "name" to name,
                    "email" to email,
                    "username" to username
                )
                db.collection("users").document(result.user?.uid!!).set(user).await()
            } catch (e: Exception) {
                // Handle registration error
            }
        }
    }
}