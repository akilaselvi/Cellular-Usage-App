package com.android.application.cellularusagedemoapp.ui.screen.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun UsageItem(label: String, used: Float, quota: Float, onClick: (() -> Unit)? = null) {
    val ratio = if (quota <= 0f) 0f else (used / quota).coerceIn(0f, 1f)
    val anim by animateFloatAsState(targetValue = ratio)


    Column(Modifier.fillMaxWidth().clickable(enabled = onClick != null) { onClick?.invoke() }) {
        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(label)
            Text("${used.toInt()} / ${quota.toInt()}")
        }
        LinearProgressIndicator(progress = anim, modifier = Modifier.fillMaxWidth().height(8.dp).clip(RoundedCornerShape(4.dp)))
        Spacer(Modifier.height(8.dp))
    }
}