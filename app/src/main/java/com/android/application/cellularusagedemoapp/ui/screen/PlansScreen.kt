package com.android.application.cellularusagedemoapp.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.android.application.cellularusagedemoapp.data.Plan
import com.android.application.cellularusagedemoapp.util.showShortToast
import com.android.application.cellularusagedemoapp.viewmodel.UsageViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PlansScreen(nav: NavController, vm: UsageViewModel = hiltViewModel()) {
    val plans = vm.getPlans()

    Column(Modifier.padding(16.dp)) {
        Spacer(Modifier.height(8.dp))
        LazyColumn {
            items(plans) { plan ->
                PlanItem(plan, nav)
            }
        }
    }
}

@Composable
fun PlanItem(plan: Plan, nav: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            Column(Modifier.weight(1f)) {
                Text(plan.name)
                Text(plan.price)
                Text("Data: ${plan.data}")
                Text("Minutes: ${plan.minutes} SMS: ${plan.sms}")
            }
            Button(onClick = {
                nav.context.showShortToast("Subscribed to ${plan.name}")
            }) {
                Text("Subscribe")
            }
        }
    }
}