package com.sermomemo.feature.language.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(
    tableName = "languages",
    indices = [Index(value = ["status"])],
)
data class LanguageEntity(
    @PrimaryKey val id: String,
    val name: String,
    val code: String?,
    @ColumnInfo(name = "base_language_code") val baseLanguageCode: String?,
    val level: String?,
    @ColumnInfo(name = "color_token") val colorToken: String,
    val icon: String?,
    val status: String,
    @ColumnInfo(name = "created_at") val createdAt: Instant,
    @ColumnInfo(name = "updated_at") val updatedAt: Instant,
)
