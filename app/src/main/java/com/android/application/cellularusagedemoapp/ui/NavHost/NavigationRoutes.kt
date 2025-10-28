package com.android.application.cellularusagedemoapp.ui.NavHost

sealed class Screen(val route: String, val title: String? = null) {
    object Splash : Screen("splash")
    object Main : Screen("main")
    object Dashboard : Screen("dashboard", "Dashboard")
    object Plans : Screen("plans", "Plans")
    object Settings : Screen("settings", "Settings")
}