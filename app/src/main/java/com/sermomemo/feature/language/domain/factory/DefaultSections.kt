package com.sermomemo.feature.language.domain.factory

import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.core.domain.id.SectionId
import com.sermomemo.feature.language.domain.enums.SectionType
import com.sermomemo.feature.language.domain.enums.SectionVisibility
import com.sermomemo.feature.language.domain.model.Section
import java.time.Instant

/**
 * The structure a new language starts with. It is a starting point, not a cage: the user may
 * rename, reorder, hide or delete any of it.
 *
 * Names arrive already localized from the presentation layer, since the domain has no access to
 * resources and these strings are user-visible.
 */
object DefaultSections {

    /** Order matters: it becomes each section's initial position. */
    val types: List<SectionType> = listOf(
        SectionType.VOCABULARY,
        SectionType.GRAMMAR,
        SectionType.EXPRESSIONS,
        SectionType.ERRORS,
        SectionType.NOTES,
    )

    /** Free-form notes are worth keeping but not worth drilling. */
    private fun practiceEnabled(type: SectionType): Boolean = type != SectionType.NOTES

    fun forLanguage(
        languageId: LanguageId,
        names: Map<SectionType, String>,
        now: Instant,
    ): List<Section> = types.mapIndexedNotNull { index, type ->
        val name = names[type] ?: return@mapIndexedNotNull null
        Section(
            id = SectionId.new(),
            languageId = languageId,
            parentSectionId = null,
            name = name,
            type = type,
            icon = null,
            colorToken = null,
            position = index,
            practiceEnabled = practiceEnabled(type),
            visibility = SectionVisibility.VISIBLE,
            createdAt = now,
            updatedAt = now,
        )
    }
}
