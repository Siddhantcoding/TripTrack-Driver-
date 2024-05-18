package com.example.triptrackdriver.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.triptrackdriver.R
import com.example.triptrackdriver.ui.theme.TripTrackDriverTheme

@Composable
fun LoginScreen(
    state: LoginState = LoginState(),
    onEvent: (LoginEvent) -> Unit,
    onNavigateToRegister: () -> Unit,
    gotoHome: () -> Unit
) {

    if (state.isLoginSuccess){
        gotoHome()
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = .9f)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_triptrack),
            contentDescription = "Logo",
            modifier = Modifier.size(150.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = state.email,
            onValueChange = { onEvent(LoginEvent.SetEmail(it)) },
            label = { Text("Email") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = state.password,
            onValueChange = { onEvent(LoginEvent.SetPassword(it)) },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        TextField(value = state.VehicleType, onValueChange ={onEvent(LoginEvent.SetVehicleType(it))} )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onEvent(LoginEvent.OnLogin)}) {
            Text("Login")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Don't have an account?",
            modifier = Modifier.clickable {
                onNavigateToRegister()
            }
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun LoginScreenPreview() {
    TripTrackDriverTheme {
        LoginScreen(state = LoginState(), onEvent = {}, onNavigateToRegister = {}, gotoHome = {})
    }

}



