package com.sermomemo.feature.language.usecase

import com.sermomemo.core.domain.DomainError
import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.core.domain.UseCaseResult
import com.sermomemo.feature.language.domain.repository.LanguageRepository
import javax.inject.Inject

/**
 * Irreversible: sections, concepts, cards and review history go with it (ON DELETE CASCADE).
 *
 * The UI must ask for reinforced confirmation before calling this (S103), and offer to export the
 * language first. Archiving is what most users actually want.
 */
class DeleteLanguage @Inject constructor(
    private val languages: LanguageRepository,
) {
    suspend operator fun invoke(id: LanguageId): UseCaseResult<Unit> {
        languages.findById(id) ?: return UseCaseResult.Failure(DomainError.LanguageNotFound)
        languages.delete(id)
        return UseCaseResult.Success(Unit)
    }
}
