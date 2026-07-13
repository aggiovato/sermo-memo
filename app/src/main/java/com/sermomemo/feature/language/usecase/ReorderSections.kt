package com.sermomemo.feature.language.usecase

import com.sermomemo.core.domain.DomainError
import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.core.domain.id.SectionId
import com.sermomemo.core.domain.UseCaseResult
import com.sermomemo.feature.language.domain.repository.SectionRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Takes the sections of one level in their new order and renumbers them 0..n-1.
 *
 * The caller sends the whole level, not a single move: that keeps positions dense and unique
 * without having to reason about what the drag displaced.
 */
class ReorderSections @Inject constructor(
    private val sections: SectionRepository,
) {
    suspend operator fun invoke(
        languageId: LanguageId,
        parentSectionId: SectionId?,
        orderedIds: List<SectionId>,
    ): UseCaseResult<Unit> {
        val level = sections.observeByLanguage(languageId).first()
            .filter { it.parentSectionId == parentSectionId }

        // The order must name exactly the level it reorders, or we would be dropping sections.
        if (level.map { it.id }.toSet() != orderedIds.toSet()) {
            return UseCaseResult.Failure(DomainError.SectionNotFound)
        }

        sections.updatePositions(orderedIds.withIndex().associate { (index, id) -> id to index })
        return UseCaseResult.Success(Unit)
    }
}
