package com.sermomemo.feature.language.data.room.mapper

import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.core.domain.id.SectionId
import com.sermomemo.feature.language.domain.model.Section
import com.sermomemo.feature.language.domain.enums.SectionType
import com.sermomemo.feature.language.domain.enums.SectionVisibility
import com.sermomemo.feature.language.data.room.entity.SectionEntity

fun SectionEntity.toDomain(): Section = Section(
    id = SectionId(id),
    languageId = LanguageId(languageId),
    parentSectionId = parentSectionId?.let(::SectionId),
    name = name,
    type = SectionType.valueOf(type),
    icon = icon,
    colorToken = colorToken,
    position = position,
    practiceEnabled = practiceEnabled,
    visibility = SectionVisibility.valueOf(visibility),
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun Section.toEntity(): SectionEntity = SectionEntity(
    id = id.value,
    languageId = languageId.value,
    parentSectionId = parentSectionId?.value,
    name = name,
    type = type.name,
    icon = icon,
    colorToken = colorToken,
    position = position,
    practiceEnabled = practiceEnabled,
    visibility = visibility.name,
    createdAt = createdAt,
    updatedAt = updatedAt,
)
