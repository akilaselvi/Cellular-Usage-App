package com.android.application.cellularusagedemoapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.application.cellularusagedemoapp.data.PromoBanner
import com.android.application.cellularusagedemoapp.repository.UsageRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DashboardViewModel(private val repo: UsageRepository) : ViewModel() {
    private val _promos = MutableStateFlow<List<PromoBanner>>(emptyList())
    val promos = _promos.asStateFlow()

    fun loadPromotions() {
        viewModelScope.launch {
            _promos.value = repo.getPromoBanners()
        }
    }
}