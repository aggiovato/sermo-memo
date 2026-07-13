package com.sermomemo.feature.language.usecase

import com.sermomemo.core.domain.DomainError
import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.core.domain.id.SectionId
import com.sermomemo.core.domain.UseCaseResult
import com.sermomemo.feature.language.domain.model.Section
import com.sermomemo.feature.language.domain.enums.SectionType
import com.sermomemo.feature.language.domain.enums.SectionVisibility
import com.sermomemo.feature.language.domain.repository.LanguageRepository
import com.sermomemo.feature.language.domain.repository.SectionRepository
import kotlinx.coroutines.flow.first
import java.time.Clock
import javax.inject.Inject

class CreateSection @Inject constructor(
    private val languages: LanguageRepository,
    private val sections: SectionRepository,
    private val clock: Clock,
) {

    data class Command(
        val languageId: LanguageId,
        val name: String,
        val type: SectionType,
        val parentSectionId: SectionId? = null,
        val icon: String? = null,
        val colorToken: String? = null,
        val practiceEnabled: Boolean = true,
    )

    suspend operator fun invoke(command: Command): UseCaseResult<SectionId> {
        val name = command.name.trim()
        if (name.isEmpty()) {
            return UseCaseResult.Failure(DomainError.EmptyName)
        }
        languages.findById(command.languageId)
            ?: return UseCaseResult.Failure(DomainError.LanguageNotFound)

        if (command.parentSectionId != null) {
            sections.findById(command.parentSectionId)
                ?: return UseCaseResult.Failure(DomainError.SectionNotFound)
        }

        // Position is unique per level, so a new section lands after the last of its siblings.
        val siblings = sections.observeByLanguage(command.languageId).first()
            .filter { it.parentSectionId == command.parentSectionId }
        val position = (siblings.maxOfOrNull { it.position } ?: -1) + 1

        val now = clock.instant()
        val section = Section(
            id = SectionId.new(),
            languageId = command.languageId,
            parentSectionId = command.parentSectionId,
            name = name,
            type = command.type,
            icon = command.icon,
            colorToken = command.colorToken,
            position = position,
            practiceEnabled = command.practiceEnabled,
            visibility = SectionVisibility.VISIBLE,
            createdAt = now,
            updatedAt = now,
        )
        sections.save(section)
        return UseCaseResult.Success(section.id)
    }
}
