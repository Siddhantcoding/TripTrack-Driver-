// app/src/main/java/com/example/triptrackdriver/screen/navigation/Navigation.kt
package com.example.triptrackdriver.screen.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.triptrackdriver.screen.driverstatus.DriverStatusScreen
import com.example.triptrackdriver.screen.location.LocationScreen


enum class MainScreen(val route: String) {
    Home("home"),
}

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = MainScreen.Home.route) {
        composable(MainScreen.Home.route) {
            DriverStatusScreen()
        }
    }
}