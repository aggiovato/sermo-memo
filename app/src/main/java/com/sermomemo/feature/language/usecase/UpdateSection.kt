package com.sermomemo.feature.language.usecase

import com.sermomemo.core.domain.DomainError
import com.sermomemo.core.domain.id.SectionId
import com.sermomemo.core.domain.UseCaseResult
import com.sermomemo.feature.language.domain.model.Section
import com.sermomemo.feature.language.domain.enums.SectionVisibility
import com.sermomemo.feature.language.domain.repository.SectionRepository
import kotlinx.coroutines.flow.first
import java.time.Clock
import javax.inject.Inject

class UpdateSection @Inject constructor(
    private val sections: SectionRepository,
    private val clock: Clock,
) {

    data class Command(
        val id: SectionId,
        val name: String,
        val icon: String?,
        val colorToken: String?,
        val practiceEnabled: Boolean,
        val visibility: SectionVisibility,
        val parentSectionId: SectionId?,
    )

    suspend operator fun invoke(command: Command): UseCaseResult<Unit> {
        val existing = sections.findById(command.id)
            ?: return UseCaseResult.Failure(DomainError.SectionNotFound)

        val name = command.name.trim()
        if (name.isEmpty()) {
            return UseCaseResult.Failure(DomainError.EmptyName)
        }

        if (command.parentSectionId != existing.parentSectionId && command.parentSectionId != null) {
            val parent = sections.findById(command.parentSectionId)
                ?: return UseCaseResult.Failure(DomainError.SectionNotFound)
            val tree = sections.observeByLanguage(existing.languageId).first()
            if (wouldCreateCycle(child = existing.id, newParent = parent.id, tree = tree)) {
                return UseCaseResult.Failure(DomainError.SectionCycle)
            }
        }

        sections.save(
            existing.copy(
                name = name,
                icon = command.icon,
                colorToken = command.colorToken,
                practiceEnabled = command.practiceEnabled,
                visibility = command.visibility,
                parentSectionId = command.parentSectionId,
                updatedAt = clock.instant(),
            ),
        )
        return UseCaseResult.Success(Unit)
    }

    /** A section cannot hang from itself nor from any of its own descendants. */
    private fun wouldCreateCycle(
        child: SectionId,
        newParent: SectionId,
        tree: List<Section>,
    ): Boolean {
        if (child == newParent) return true
        val parentOf = tree.associate { it.id to it.parentSectionId }
        var cursor: SectionId? = newParent
        while (cursor != null) {
            if (cursor == child) return true
            cursor = parentOf[cursor]
        }
        return false
    }
}
