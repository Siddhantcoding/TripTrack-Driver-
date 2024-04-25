package com.example.triptrackdriver.screen.navigation

import RegisterScreen
import RegisterViewModel
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.triptrackdriver.screen.driverstatus.DriverStatusScreen
import com.example.triptrackdriver.screen.login.LoginScreen
import com.example.triptrackdriver.screen.login.LoginViewModel


enum class AuthScreen(val route: String) {
    Login("login"),
    Register("register"),
    ForgotPassword("forgotPassword"),
    DriverStatus("driverStatus")
}

@Composable
fun AuthNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AuthScreen.Login.route) {
        composable(AuthScreen.Login.route) {
            val vm: LoginViewModel = viewModel()
            LoginScreen(onEvent = vm::onEvent) {
                navController.navigate(AuthScreen.Register.route)
            }
        }
        composable(AuthScreen.Register.route) {
            val vm : RegisterViewModel = viewModel()
            RegisterScreen(state = vm.state, onEvent = vm::onEvent) {
                navController.navigate(AuthScreen.ForgotPassword.route)
            }
        }
        composable(AuthScreen.ForgotPassword.route) {
            // ForgotPasswordScreen goes here
        }
        composable(AuthScreen.DriverStatus.route) {
            DriverStatusScreen()
        }
    }
}