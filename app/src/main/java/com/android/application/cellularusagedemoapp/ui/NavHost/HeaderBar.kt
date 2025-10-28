package com.android.application.cellularusagedemoapp.ui.NavHost

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(navController: NavHostController) {
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination?.route

    val title = when (currentDestination) {
        Screen.Dashboard.route -> Screen.Dashboard.title
        Screen.Plans.route -> Screen.Plans.title
        Screen.Settings.route -> Screen.Settings.title
        else -> ""
    }

    TopAppBar(
        title = { title?.let { Text(it) } },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.White
        )
    )
}