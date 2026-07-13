package com.sermomemo.feature.language.usecase

import com.sermomemo.core.domain.DomainError
import com.sermomemo.core.domain.id.SectionId
import com.sermomemo.core.domain.UseCaseResult
import com.sermomemo.feature.language.domain.enums.SectionVisibility
import com.sermomemo.feature.language.domain.repository.SectionRepository
import kotlinx.coroutines.flow.first
import java.time.Clock
import javax.inject.Inject

/**
 * Deleting a section takes its subsections and concepts with it (ON DELETE CASCADE), so the UI
 * must confirm and say how many concepts are at stake.
 *
 * Hiding is the reversible alternative and is what the "hide" affordance uses.
 */
class DeleteSection @Inject constructor(
    private val sections: SectionRepository,
) {
    suspend operator fun invoke(id: SectionId): UseCaseResult<Unit> {
        val section = sections.findById(id)
            ?: return UseCaseResult.Failure(DomainError.SectionNotFound)

        // An active language always keeps at least one section: otherwise there is nowhere to
        // capture, and the notebook stops being a notebook.
        val roots = sections.observeByLanguage(section.languageId).first()
            .filter { it.parentSectionId == null }
        if (section.parentSectionId == null && roots.size <= 1) {
            return UseCaseResult.Failure(DomainError.LastSectionCannotBeRemoved)
        }

        sections.delete(id)
        return UseCaseResult.Success(Unit)
    }
}
