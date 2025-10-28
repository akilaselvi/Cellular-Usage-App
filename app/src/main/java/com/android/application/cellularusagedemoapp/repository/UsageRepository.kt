package com.android.application.cellularusagedemoapp.repository

import com.android.application.cellularusagedemoapp.data.Plan
import com.android.application.cellularusagedemoapp.data.PromoBanner
import com.android.application.cellularusagedemoapp.data.UsageState
import jakarta.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.Calendar

class UsageRepository @Inject constructor() {
    private val _usageFlow = MutableStateFlow<UsageState>(generateInitialUsage())
    val usageFlow: StateFlow<UsageState> = _usageFlow


    fun getPlans() = listOf(
        Plan("super299", "Super 299", "₹299", "3GB/day", "1000", "100"),
        Plan("max499", "Max 499", "₹499", "5GB/day", "Unlimited", "500"),
        Plan("power799", "Power 799", "₹799", "10GB/day", "Unlimited", "1000")
    )


    fun getPromoBanners() = listOf(
        PromoBanner("b1", "Super Saver 499", "Save more with Super Saver 499"),
        PromoBanner("b2", "Weekend Data Boost", "Get extra data on weekends")
    )


    suspend fun simulateUsageDrain() {
        repeat(10) {
            delay(2000)
            val current = _usageFlow.value
            val newUsage = current.copy(
                dataUsedMb = (current.dataUsedMb + 50L * 1024 * 1024).coerceAtMost(current.dataQuotaMb),
                minutesUsed = (current.minutesUsed + 10).coerceAtMost(current.minutesQuota),
                smsUsed = (current.smsUsed + 5).coerceAtMost(current.smsQuota)
            )
            _usageFlow.value = newUsage
        }
    }


    private fun generateInitialUsage(): UsageState {
        val now = Calendar.getInstance()
        now.add(Calendar.DAY_OF_MONTH, 3)
        return UsageState(
            dataUsedMb = 2_300_000_000L, // 2.3GB
            dataQuotaMb = 5_000_000_000L, // 5GB
            minutesUsed = 340,
            minutesQuota = 1000,
            smsUsed = 120,
            smsQuota = 500,
            balance = 45.0,
            renewalDateMillis = now.timeInMillis
        )
    }
}