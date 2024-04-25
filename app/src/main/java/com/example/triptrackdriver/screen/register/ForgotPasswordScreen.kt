package com.example.triptrackdriver.screen.register

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ForgotPasswordScreen() {
    var email by remember { mutableStateOf("") }

    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = "Forgot Password")
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.padding(top = 16.dp)
        )
        Button(
            onClick = { /* TODO: Implement forgot password logic here */ },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Submit")
        }
    }
}
