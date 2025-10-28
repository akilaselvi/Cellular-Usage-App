package com.android.application.cellularusagedemoapp.di

import com.android.application.cellularusagedemoapp.repository.UsageRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Module {
    @Provides
    @Singleton
    fun provideRepository(): UsageRepository = UsageRepository()
}