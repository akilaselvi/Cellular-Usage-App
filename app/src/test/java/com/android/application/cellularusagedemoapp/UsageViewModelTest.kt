package com.android.application.cellularusagedemoapp

import com.android.application.cellularusagedemoapp.data.PromoBanner
import com.android.application.cellularusagedemoapp.repository.UsageRepository
import com.android.application.cellularusagedemoapp.viewmodel.DashboardViewModel
import com.android.application.cellularusagedemoapp.viewmodel.UsageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

@OptIn(ExperimentalCoroutinesApi::class)
class UsageViewModelTest {
    private lateinit var repo: UsageRepository
    private lateinit var vm: UsageViewModel


    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
        repo = UsageRepository()
        vm = UsageViewModel(repo)
    }


    @Test
    fun initialUsageAvailable() = runTest {
        val job = launch {
            vm.usage.collect { value ->
                println("Received: $value")
            }
        }
        job.cancel()
    }

    @Test
    fun dashboard_loads_promotions_successfully() = runTest {
        val fakeRepo = mock(PromoRepository::class.java)
        val promos = listOf(PromoBanner("Plan Offer", "50% off"))
        `when`(fakeRepo.getPromos()).thenReturn(promos)

        val vm = DashboardViewModel(fakeRepo)

        vm.loadPromotions()

        vm.promos.collect {
            assertEquals(promos, awaitItem())
        }
    }

}