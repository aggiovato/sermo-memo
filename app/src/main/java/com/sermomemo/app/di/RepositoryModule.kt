package com.sermomemo.app.di

import com.sermomemo.feature.language.domain.repository.LanguageRepository
import com.sermomemo.feature.language.domain.repository.SectionRepository
import com.sermomemo.feature.language.data.room.repository.RoomLanguageRepository
import com.sermomemo.feature.language.data.room.repository.RoomSectionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The only place where domain ports meet their adapters. Swapping Room for anything else (or
 * wrapping it with a sync layer) happens here and nowhere else.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLanguageRepository(impl: RoomLanguageRepository): LanguageRepository

    @Binds
    @Singleton
    abstract fun bindSectionRepository(impl: RoomSectionRepository): SectionRepository
}
