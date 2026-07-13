package com.sermomemo.app.di

import android.content.Context
import androidx.room.Room
import com.sermomemo.app.database.SermoDatabase
import com.sermomemo.feature.language.data.room.dao.LanguageDao
import com.sermomemo.feature.language.data.room.dao.SectionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): SermoDatabase =
        Room.databaseBuilder(context, SermoDatabase::class.java, SermoDatabase.NAME).build()

    @Provides
    fun provideLanguageDao(database: SermoDatabase): LanguageDao = database.languageDao()

    @Provides
    fun provideSectionDao(database: SermoDatabase): SectionDao = database.sectionDao()
}
