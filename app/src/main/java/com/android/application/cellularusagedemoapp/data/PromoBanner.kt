package com.android.application.cellularusagedemoapp.data

import kotlinx.serialization.Serializable


@Serializable
data class PromoBanner(
    val id: String,
    val title: String,
    val description: String
)