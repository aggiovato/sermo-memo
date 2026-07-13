package com.sermomemo.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sermomemo.core.database.InstantConverters
import com.sermomemo.feature.language.data.room.dao.LanguageDao
import com.sermomemo.feature.language.data.room.dao.SectionDao
import com.sermomemo.feature.language.data.room.entity.LanguageEntity
import com.sermomemo.feature.language.data.room.entity.SectionEntity

/**
 * Local source of truth. Every schema change ships an explicit migration plus a migration test:
 * destructive fallback is never enabled, in any build type.
 *
 * Entities grow with each domain (v1: languages and sections).
 */
@Database(
    entities = [
        LanguageEntity::class,
        SectionEntity::class,
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(InstantConverters::class)
abstract class SermoDatabase : RoomDatabase() {

    abstract fun languageDao(): LanguageDao

    abstract fun sectionDao(): SectionDao

    companion object {
        const val NAME = "sermomemo.db"
    }
}
