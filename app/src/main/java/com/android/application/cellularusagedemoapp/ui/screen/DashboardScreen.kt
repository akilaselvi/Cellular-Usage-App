package com.android.application.cellularusagedemoapp.ui.screen

import android.widget.Toast
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.android.application.cellularusagedemoapp.repository.UsageRepository
import com.android.application.cellularusagedemoapp.ui.screen.components.PromoCard
import com.android.application.cellularusagedemoapp.ui.screen.components.UsageItem
import com.android.application.cellularusagedemoapp.ui.theme.PurpleGrey80
import com.android.application.cellularusagedemoapp.viewmodel.UsageViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DashboardScreen(
    nav: NavController,
    vm: UsageViewModel = hiltViewModel(),
)
{
    val usage by vm.usage.collectAsState()
    val context = LocalContext.current


    LaunchedEffect(Unit) { vm.startSimulatingUpdates() }


    Column(Modifier.padding(16.dp)) {
        Spacer(Modifier.height(12.dp))


        Card(Modifier.fillMaxWidth().clickable { }, elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)) {
            Column(Modifier.padding(12.dp)) {
                Text("Usage Summary", fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))


                UsageItem("Data", usage.dataUsedMb.toFloat(), usage.dataQuotaMb.toFloat(),
                    onClick = { Toast.makeText(context, "Data details", Toast.LENGTH_SHORT).show() })


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
            PromoCard(p.title, p.description) { Toast.makeText(nav.context, "${p.title}: Know more", Toast.LENGTH_SHORT).show() }

        }
    }
}



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

@Preview(    showBackground = true,
    showSystemUi = true,
    name = "Dashboard Preview")
@Composable
fun PromotionSectionPreview(){
    val fakeVm = object : UsageViewModel(UsageRepository()) {
        override fun startSimulatingUpdates() {
            // No-op for preview
        }

    }
    MaterialTheme{
        PromotionSection(fakeVm, rememberNavController())
    }
}



