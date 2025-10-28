package com.android.application.cellularusagedemoapp.data

import kotlinx.serialization.Serializable

@Serializable
data class UsageState (
    val dataUsedMb: Long,
    val dataQuotaMb: Long,
    val minutesUsed: Int,
    val minutesQuota: Int,
    val smsUsed: Int,
    val smsQuota: Int,
    val balance: Double,
    val renewalDateMillis: Long,
    val lastUpdated: Long = System.currentTimeMillis()
){
    companion object{
        fun empty() = UsageState(0,0,0,0,0,0,0.0,0L,0L)
    }
}