package com.android.application.cellularusagedemoapp.ui.screen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.application.cellularusagedemoapp.ui.theme.PurpleGrey80

@Composable
fun PromoCard(title: String, desc: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(250.dp)
            .height(140.dp)
            .clip(RoundedCornerShape(16.dp)),
        colors = CardDefaults.cardColors(containerColor = PurpleGrey80),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(title, style = MaterialTheme.typography.titleMedium, color = Color.Black)
                Text(text = desc, style = MaterialTheme.typography.bodySmall, color = Color.Black)
//                Text(text = description, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }
            Button(
                onClick = onClick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Know More")
            }
        }
    }
}

@Preview(    showBackground = true,
    showSystemUi = true,
    name = "Dashboard Preview")
@Composable
fun PromotonCard(){
    MaterialTheme{
        PromoCard("Title", "Description"){

        }
    }
}