package com.android.application.cellularusagedemoapp.data

import kotlinx.serialization.Serializable

@Serializable
data class Plan(
    val id: String,
    val name: String,
    val price: String,
    val data: String,
    val minutes: String,
    val sms: String
)
