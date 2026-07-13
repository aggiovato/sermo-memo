package com.sermomemo.feature.language.domain.model

import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.core.domain.id.SectionId
import com.sermomemo.feature.language.domain.enums.SectionType
import com.sermomemo.feature.language.domain.enums.SectionVisibility
import java.time.Instant

/**
 * How a language is organised. Sections are the user's structure, not ours: the app ships a
 * default set and lets them rename, reorder, nest, hide or drop it entirely.
 */
data class Section(
    val id: SectionId,
    val languageId: LanguageId,
    val parentSectionId: SectionId?,
    val name: String,
    val type: SectionType,
    val icon: String?,
    val colorToken: String?,
    val position: Int,
    /** A section can be reference-only: it holds concepts that never turn into cards. */
    val practiceEnabled: Boolean,
    val visibility: SectionVisibility,
    val createdAt: Instant,
    val updatedAt: Instant,
)
