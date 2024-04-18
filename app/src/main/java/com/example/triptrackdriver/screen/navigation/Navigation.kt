// app/src/main/java/com/example/triptrackdriver/screen/navigation/Navigation.kt
package com.example.triptrackdriver.screen.navigation

import RegisterScreen
import RegisterState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "registerScreen") {
        composable("registerScreen") { RegisterScreen(
            RegisterState(isLoading = true),
            onEvent = null
        ) {
            navController.navigate(AuthScreen.Login.route)
        }
        }
        // Add other composable here
    }
}