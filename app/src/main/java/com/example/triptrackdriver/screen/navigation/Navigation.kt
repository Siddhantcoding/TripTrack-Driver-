// app/src/main/java/com/example/triptrackdriver/screen/navigation/Navigation.kt
package com.example.triptrackdriver.screen.navigation

import android.content.Context
import android.location.Geocoder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.triptrackdriver.screen.driver.DriverStatusScreen
import com.example.triptrackdriver.screen.driver.ProfileScreen
import com.example.triptrackdriver.screen.driver.ProfileViewModel
import com.example.triptrackdriver.screen.driver.StopLocationScreen
import com.example.triptrackdriver.screen.location.LocationScreen


enum class MainScreen(val route: String) {
    Home("home"),
    MapView("map"),
    Profile("profile"),
    StopPoints("stop_points"),
}

@Composable
fun Navigation(context: Context) {
    val navController = rememberNavController()
    val geocoder = Geocoder(context, context.resources.configuration.locales[0])
    // send the geocoder to the view model
    val profileVM: ProfileViewModel = viewModel(factory = ProfileViewModel.Factory(geocoder))

    NavHost(navController, startDestination = MainScreen.Home.route) {
        composable(MainScreen.Home.route) {
            DriverStatusScreen (
                onGoOnline = { navController.navigate(MainScreen.MapView.route) },
                onStopLocationSelection = { navController.navigate(MainScreen.StopPoints.route) },
                onUpdateProfile = { navController.navigate(MainScreen.Profile.route) }
            )
        }
        composable(MainScreen.MapView.route) {
            LocationScreen()
        }
        composable(MainScreen.Profile.route) {
            ProfileScreen(
                state = profileVM.state.collectAsState().value,
                onEvent = { profileVM.onEvent(it) },
                onBack = { navController.popBackStack() }
            )
        }
        composable(MainScreen.StopPoints.route) {
            StopLocationScreen(
                state = profileVM.state.collectAsState().value,
                onEvent = { profileVM.onEvent(it) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}





