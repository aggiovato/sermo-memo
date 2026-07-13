package com.sermomemo.feature.language.usecase

import com.sermomemo.core.domain.DomainError
import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.core.domain.UseCaseResult
import com.sermomemo.feature.language.domain.model.Language
import com.sermomemo.feature.language.domain.enums.LanguageLevel
import com.sermomemo.feature.language.domain.repository.LanguageRepository
import com.sermomemo.feature.language.domain.enums.LanguageStatus
import com.sermomemo.feature.language.domain.factory.DefaultSections
import com.sermomemo.feature.language.domain.repository.SectionRepository
import com.sermomemo.feature.language.domain.enums.SectionType
import java.time.Clock
import java.time.Instant
import javax.inject.Inject

/**
 * Creates a study space and the structure it needs to be usable from the first second.
 *
 * The plan limit is checked here rather than in the UI: it is a business rule, and it must hold
 * whichever screen (or import) triggers the creation.
 */
class CreateLanguage @Inject constructor(
    private val languages: LanguageRepository,
    private val sections: SectionRepository,
    private val clock: Clock,
) {

    data class Command(
        val name: String,
        val code: String? = null,
        val baseLanguageCode: String? = null,
        val level: LanguageLevel? = null,
        val colorToken: String,
        /** Localised names for the starter sections, resolved by the caller. */
        val defaultSectionNames: Map<SectionType, String>,
        /** Active-language allowance of the current plan. Free = 2. */
        val maxActiveLanguages: Int,
    )

    suspend operator fun invoke(command: Command): UseCaseResult<LanguageId> {
        val name = command.name.trim()
        if (name.isEmpty()) {
            return UseCaseResult.Failure(DomainError.EmptyName)
        }
        if (languages.existsWithName(name)) {
            return UseCaseResult.Failure(DomainError.DuplicateLanguageName)
        }

        val activeCount = languages.countByStatus(LanguageStatus.ACTIVE)
        if (activeCount >= command.maxActiveLanguages) {
            return UseCaseResult.Failure(DomainError.PlanLimitReached(command.maxActiveLanguages))
        }

        val now: Instant = clock.instant()
        val language = Language(
            id = LanguageId.new(),
            name = name,
            code = command.code?.trim()?.takeIf { it.isNotEmpty() },
            baseLanguageCode = command.baseLanguageCode,
            level = command.level,
            colorToken = command.colorToken,
            icon = null,
            status = LanguageStatus.ACTIVE,
            createdAt = now,
            updatedAt = now,
        )

        languages.save(language)
        sections.saveAll(
            DefaultSections.forLanguage(
                languageId = language.id,
                names = command.defaultSectionNames,
                now = now,
            ),
        )

        return UseCaseResult.Success(language.id)
    }
}
