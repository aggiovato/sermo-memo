package com.sermomemo.core.database

import androidx.room.TypeConverter
import java.time.Instant

/** The domain speaks [Instant]; SQLite stores epoch milliseconds. */
class InstantConverters {

    @TypeConverter
    fun toEpochMillis(instant: Instant?): Long? = instant?.toEpochMilli()

    @TypeConverter
    fun fromEpochMillis(epochMillis: Long?): Instant? = epochMillis?.let(Instant::ofEpochMilli)
}
