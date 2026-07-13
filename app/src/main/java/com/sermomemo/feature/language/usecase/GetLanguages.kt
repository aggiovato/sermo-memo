package com.sermomemo.feature.language.usecase

import com.sermomemo.feature.language.domain.model.Language
import com.sermomemo.feature.language.domain.enums.LanguageStatus
import com.sermomemo.feature.language.domain.repository.LanguageRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/** Reading side of the language list. Observation, so the UI reacts to any write from anywhere. */
class GetLanguages @Inject constructor(
    private val languages: LanguageRepository,
) {
    operator fun invoke(status: LanguageStatus? = null): Flow<List<Language>> =
        if (status == null) languages.observeAll() else languages.observeByStatus(status)
}
