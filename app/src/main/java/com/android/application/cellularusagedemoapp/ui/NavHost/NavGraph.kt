package com.android.application.cellularusagedemoapp.ui.NavHost

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.application.cellularusagedemoapp.ui.screen.MainScreen
import com.android.application.cellularusagedemoapp.ui.screen.SplashScreen

@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) { SplashScreen(navController) }
        composable(Screen.Main.route) { MainScreen() }

    }
}