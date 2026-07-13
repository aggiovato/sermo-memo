package com.sermomemo.feature.language.usecase

import com.sermomemo.core.domain.DomainError
import com.sermomemo.core.domain.id.SectionId
import com.sermomemo.core.domain.UseCaseResult
import com.sermomemo.feature.language.domain.enums.SectionVisibility
import com.sermomemo.feature.language.domain.repository.SectionRepository
import java.time.Clock
import javax.inject.Inject

/** Reversible counterpart of [DeleteSection]: the content stays, it just leaves the way. */
class SetSectionVisibility @Inject constructor(
    private val sections: SectionRepository,
    private val clock: Clock,
) {
    suspend operator fun invoke(
        id: SectionId,
        visibility: SectionVisibility,
    ): UseCaseResult<Unit> {
        val section = sections.findById(id)
            ?: return UseCaseResult.Failure(DomainError.SectionNotFound)

        sections.save(section.copy(visibility = visibility, updatedAt = clock.instant()))
        return UseCaseResult.Success(Unit)
    }
}
