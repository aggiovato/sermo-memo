package com.sermomemo.app.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.time.Clock
import javax.inject.Singleton

/** Time is injected so use cases stay deterministic under test. */
@Module
@InstallIn(SingletonComponent::class)
object ClockModule {

    @Provides
    @Singleton
    fun provideClock(): Clock = Clock.systemUTC()
}
