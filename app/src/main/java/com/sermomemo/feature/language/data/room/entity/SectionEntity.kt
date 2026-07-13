package com.sermomemo.feature.language.data.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.Instant

@Entity(
    tableName = "sections",
    foreignKeys = [
        ForeignKey(
            entity = LanguageEntity::class,
            parentColumns = ["id"],
            childColumns = ["language_id"],
            onDelete = ForeignKey.CASCADE,
        ),
        // A subsection dies with its parent; the graph must stay acyclic and rootless-free.
        ForeignKey(
            entity = SectionEntity::class,
            parentColumns = ["id"],
            childColumns = ["parent_section_id"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [
        Index(value = ["language_id", "position"]),
        Index(value = ["parent_section_id"]),
    ],
)
data class SectionEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "language_id") val languageId: String,
    @ColumnInfo(name = "parent_section_id") val parentSectionId: String?,
    val name: String,
    val type: String,
    val icon: String?,
    @ColumnInfo(name = "color_token") val colorToken: String?,
    val position: Int,
    @ColumnInfo(name = "practice_enabled") val practiceEnabled: Boolean,
    val visibility: String,
    @ColumnInfo(name = "created_at") val createdAt: Instant,
    @ColumnInfo(name = "updated_at") val updatedAt: Instant,
)
