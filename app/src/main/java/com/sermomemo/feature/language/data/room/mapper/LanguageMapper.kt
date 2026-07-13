package com.sermomemo.feature.language.data.room.mapper

import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.feature.language.domain.model.Language
import com.sermomemo.feature.language.domain.enums.LanguageLevel
import com.sermomemo.feature.language.domain.enums.LanguageStatus
import com.sermomemo.feature.language.data.room.entity.LanguageEntity

// Enums are stored as their name, not their ordinal: reordering the enum must not silently
// reinterpret existing rows.

fun LanguageEntity.toDomain(): Language = Language(
    id = LanguageId(id),
    name = name,
    code = code,
    baseLanguageCode = baseLanguageCode,
    level = level?.let(LanguageLevel::valueOf),
    colorToken = colorToken,
    icon = icon,
    status = LanguageStatus.valueOf(status),
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun Language.toEntity(): LanguageEntity = LanguageEntity(
    id = id.value,
    name = name,
    code = code,
    baseLanguageCode = baseLanguageCode,
    level = level?.name,
    colorToken = colorToken,
    icon = icon,
    status = status.name,
    createdAt = createdAt,
    updatedAt = updatedAt,
)
