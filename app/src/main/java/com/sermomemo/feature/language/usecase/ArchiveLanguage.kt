package com.sermomemo.feature.language.usecase

import com.sermomemo.core.domain.DomainError
import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.core.domain.UseCaseResult
import com.sermomemo.feature.language.domain.enums.LanguageStatus
import com.sermomemo.feature.language.domain.repository.LanguageRepository
import javax.inject.Inject

/**
 * Archiving is not deleting: concepts, cards and review history stay untouched, and the language
 * stops counting against the plan's active allowance.
 */
class ArchiveLanguage @Inject constructor(
    private val languages: LanguageRepository,
) {
    suspend operator fun invoke(id: LanguageId): UseCaseResult<Unit> {
        languages.findById(id) ?: return UseCaseResult.Failure(DomainError.LanguageNotFound)
        languages.updateStatus(id, LanguageStatus.ARCHIVED)
        return UseCaseResult.Success(Unit)
    }
}
