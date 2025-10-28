package com.android.application.cellularusagedemoapp.viewmodel

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.application.cellularusagedemoapp.repository.UsageRepository
import com.android.application.cellularusagedemoapp.data.UsageState
import com.android.application.cellularusagedemoapp.util.NotificationHelper
import dagger.hilt.android.internal.Contexts
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
open class UsageViewModel @Inject constructor(private val repo: UsageRepository) : ViewModel() {
    val usage: StateFlow<UsageState> = repo.usageFlow
        .stateIn(viewModelScope, SharingStarted.Companion.Lazily, repo.usageFlow.value)


    open fun startSimulatingUpdates() {
        viewModelScope.launch { repo.simulateUsageDrain() }
    }


    fun getPlans() = repo.getPlans()
    fun getPromotions() = repo.getPromoBanners()
}