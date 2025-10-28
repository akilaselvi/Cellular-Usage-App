package com.android.application.cellularusagedemoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.android.application.cellularusagedemoapp.ui.NavHost.AppNavHost
import com.android.application.cellularusagedemoapp.ui.screen.DashboardScreen
import com.android.application.cellularusagedemoapp.ui.screen.PlansScreen
import com.android.application.cellularusagedemoapp.ui.screen.SettingsScreen
import com.android.application.cellularusagedemoapp.ui.theme.CellularUsageDemoAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CellularUsageDemoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.onSecondary
                ) {
                    val navController = rememberNavController()

                    AppNavHost(navController = navController)

                }

            }
        }
    }
}
