package com.example.triptrackdriver.screen.navigation

import RegisterScreen
import RegisterState
import RegisterViewModel
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.triptrackdriver.screen.login.LoginScreen
import com.example.triptrackdriver.screen.login.LoginState
import com.example.triptrackdriver.screen.login.LoginViewModel


enum class AuthScreen(val route: String) {

    Login("login"),
    Register("register"),
    ForgotPassword("forgotPassword")
}

@Composable
fun AuthNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AuthScreen.Login.route) {
        composable(AuthScreen.Login.route) {
            val vm: LoginViewModel = viewModel()
            LoginScreen(state = LoginState(isLoading = true), onEvent = vm::onEvent) {
                navController.navigate(AuthScreen.Register.route)
            }
//            val vm: LoginViewModel = viewModel()
//            LoginScreen(
//                state = vm.state.collectAsState().value,
//                onEvent = vm::onEvent,
//                onNavigateToRegister = {
//                    navController.navigate(AuthScreen.Register.route)
//                },
//                onNavigateToHome = {
//                    navController.navigate(AuthScreen.Home.route)
//                })
        }
        composable(AuthScreen.Register.route) {
            val vm : RegisterViewModel = viewModel()
//              val vm: RegisterViewModel = viewModel()
//            RegisterScreen(
//                state = vm.state.collectAsState().value,
//                onEvent = vm::onEvent,
//                onBack = {
//                    navController.popBackStack()
//                })
        }
        composable(AuthScreen.ForgotPassword.route) {
            val vm: RegisterViewModel = viewModel()
//            val vm: ForgotPasswordViewModel = viewModel()
        }
    }
}