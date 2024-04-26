// app/src/main/java/com/example/triptrackdriver/screen/navigation/Navigation.kt
package com.example.triptrackdriver.screen.navigation

import RegisterScreen
import RegisterViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.triptrackdriver.screen.login.LoginScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "registerScreen") {
        composable("registerScreen") {
            val viewModel: RegisterViewModel = viewModel()
            RegisterScreen(
                state = viewModel.state,
                onEvent = viewModel::onEvent
            ) {
                navController.navigate("loginScreen")
            }
        }
        composable("loginScreen") {
            LoginScreen(
                onEvent = { /* Handle login events here */ },
                onNavigateToRegister = { navController.navigate("registerScreen") }
            )
        }
        // Add other composables here
    }
}