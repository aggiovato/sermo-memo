package com.sermomemo.feature.language.domain.model

import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.feature.language.domain.enums.LanguageLevel
import com.sermomemo.feature.language.domain.enums.LanguageStatus
import java.time.Instant

/**
 * A study space. Everything the user captures hangs from one of these.
 *
 * Archiving keeps all data: only creation is ever limited by the plan, never reading or reviewing
 * what already exists.
 */
data class Language(
    val id: LanguageId,
    val name: String,
    val code: String?,
    val baseLanguageCode: String?,
    val level: LanguageLevel?,
    val colorToken: String,
    val icon: String?,
    val status: LanguageStatus,
    val createdAt: Instant,
    val updatedAt: Instant,
)
