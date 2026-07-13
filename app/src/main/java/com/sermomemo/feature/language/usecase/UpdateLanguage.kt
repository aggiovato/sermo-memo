package com.sermomemo.feature.language.usecase

import com.sermomemo.core.domain.DomainError
import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.core.domain.UseCaseResult
import com.sermomemo.feature.language.domain.enums.LanguageLevel
import com.sermomemo.feature.language.domain.repository.LanguageRepository
import java.time.Clock
import javax.inject.Inject

class UpdateLanguage @Inject constructor(
    private val languages: LanguageRepository,
    private val clock: Clock,
) {

    data class Command(
        val id: LanguageId,
        val name: String,
        val code: String?,
        val baseLanguageCode: String?,
        val level: LanguageLevel?,
        val colorToken: String,
        val icon: String?,
    )

    suspend operator fun invoke(command: Command): UseCaseResult<Unit> {
        val existing = languages.findById(command.id)
            ?: return UseCaseResult.Failure(DomainError.LanguageNotFound)

        val name = command.name.trim()
        if (name.isEmpty()) {
            return UseCaseResult.Failure(DomainError.EmptyName)
        }
        // Renaming to your own name is not a duplicate.
        if (!name.equals(existing.name, ignoreCase = true) && languages.existsWithName(name)) {
            return UseCaseResult.Failure(DomainError.DuplicateLanguageName)
        }

        languages.save(
            existing.copy(
                name = name,
                code = command.code?.trim()?.takeIf { it.isNotEmpty() },
                baseLanguageCode = command.baseLanguageCode,
                level = command.level,
                colorToken = command.colorToken,
                icon = command.icon,
                updatedAt = clock.instant(),
            ),
        )
        return UseCaseResult.Success(Unit)
    }
}
