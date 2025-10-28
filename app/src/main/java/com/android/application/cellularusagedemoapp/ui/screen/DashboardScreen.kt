package com.android.application.cellularusagedemoapp.ui.screen

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import com.android.application.cellularusagedemoapp.repository.UsageRepository
import com.android.application.cellularusagedemoapp.ui.screen.components.PromoCard
import com.android.application.cellularusagedemoapp.ui.screen.components.UsageItem
import com.android.application.cellularusagedemoapp.util.NotificationHelper
import com.android.application.cellularusagedemoapp.util.showLongToast
import com.android.application.cellularusagedemoapp.util.showShortToast
import com.android.application.cellularusagedemoapp.viewmodel.UsageViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private val Context.dataStore by preferencesDataStore("settings")

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardScreen(
    nav: NavController,
    vm: UsageViewModel = hiltViewModel(),
) {
    val usage by vm.usage.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(Unit) {
        vm.startSimulatingUpdates()
        NotificationHelper.createChannel(nav.context)
        val currentUsage = 6.0
        val limit = 5.0
        if (currentUsage > limit) {
            NotificationHelper.createChannel(context)
            NotificationHelper.notifyLowBalance(
                context,
                usage.dataUsedMb.toString(),
                "You have crossed your data limit!"
            )
        }
    }


    Column(Modifier.padding(16.dp)) {
        Spacer(Modifier.height(12.dp))


        Card(
            Modifier
                .fillMaxWidth()
                .clickable { }, elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column(Modifier.padding(12.dp)) {
                Text("Usage Summary", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))


                UsageItem(
                    "Data", usage.dataUsedMb.toFloat(), usage.dataQuotaMb.toFloat(),
                    onClick = {
                        context.showShortToast("Data details")
                    })


                UsageItem("Minutes", usage.minutesUsed.toFloat(), usage.minutesQuota.toFloat())

                UsageItem("SMS", usage.smsUsed.toFloat(), usage.smsQuota.toFloat())


                Spacer(Modifier.height(8.dp))
                val sdf = SimpleDateFormat("MMM d, yyyy", Locale.getDefault())
                Text("Renewal Date: ${sdf.format(Date(usage.renewalDateMillis))}")
                Text("Balance: ₹${usage.balance}")
            }
        }


        Spacer(Modifier.height(12.dp))

        Text("Promotions", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))


        // Promotion Section Screen
        PromotionSection(vm, nav)


        Spacer(Modifier.height(12.dp))
        Text("Available Plans", fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Button(onClick = { nav.navigate("plans") }) { Text("View Plans") }


        Spacer(Modifier.height(8.dp))
        OutlinedButton(onClick = { nav.navigate("settings") }) { Text("Settings") }

    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PromotionSection(vm: UsageViewModel, nav: NavController) {
    val promos = vm.getPromotions()

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(horizontal = 16.dp)
    ) {
        items(promos) { p ->
            PromoCard(p.title, p.description) {
                nav.context.showShortToast("${p.title}: Know more")
            }

        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Dashboard Preview"
)
@Composable
fun DashboardPreview() {
    // Fake NavController for preview (not functional)
    val navController = rememberNavController()

    val fakeVm = object : UsageViewModel(UsageRepository()) {
        override fun startSimulatingUpdates() {
            // No-op for preview
        }
    }



    MaterialTheme {
        DashboardScreen(navController, fakeVm)
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "Dashboard Preview"
)
@Composable
fun PromotionSectionPreview() {
    val navController = rememberNavController()
    val context = navController.context

    val dataStoreInstance = context.dataStore


    val fakeVm = @RequiresApi(Build.VERSION_CODES.O)
    object : UsageViewModel(UsageRepository()) {
        override fun startSimulatingUpdates() {
            // No-op for preview
        }

    }
    MaterialTheme {
        PromotionSection(fakeVm, rememberNavController())
    }
}



