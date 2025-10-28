package com.android.application.cellularusagedemoapp.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SettingsScreen(nav: NavController) {
    Column(Modifier.padding(16.dp)) {
        SpacerSmall()
        var notificationsEnabled by remember { mutableStateOf(true) }
        RowSetting("Enable notifications", notificationsEnabled) { notificationsEnabled = it }
    }
}


@Composable
fun RowSetting(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    androidx.compose.foundation.layout.Row(Modifier.padding(vertical = 8.dp), verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        Text(title, modifier = Modifier.weight(1f))
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}


@Composable
fun SpacerSmall() = androidx.compose.foundation.layout.Spacer(modifier = Modifier.padding(4.dp))