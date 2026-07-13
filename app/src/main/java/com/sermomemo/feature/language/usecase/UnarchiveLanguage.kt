package com.sermomemo.feature.language.usecase

import com.sermomemo.core.domain.DomainError
import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.core.domain.UseCaseResult
import com.sermomemo.feature.language.domain.enums.LanguageStatus
import com.sermomemo.feature.language.domain.repository.LanguageRepository
import javax.inject.Inject

/**
 * Bringing a language back is a creation of sorts, so the plan limit applies again: an expired
 * subscription must never lock the data, but it can stop it from becoming active once more.
 */
class UnarchiveLanguage @Inject constructor(
    private val languages: LanguageRepository,
) {
    suspend operator fun invoke(id: LanguageId, maxActiveLanguages: Int): UseCaseResult<Unit> {
        languages.findById(id) ?: return UseCaseResult.Failure(DomainError.LanguageNotFound)

        val activeCount = languages.countByStatus(LanguageStatus.ACTIVE)
        if (activeCount >= maxActiveLanguages) {
            return UseCaseResult.Failure(DomainError.PlanLimitReached(maxActiveLanguages))
        }

        languages.updateStatus(id, LanguageStatus.ACTIVE)
        return UseCaseResult.Success(Unit)
    }
}
