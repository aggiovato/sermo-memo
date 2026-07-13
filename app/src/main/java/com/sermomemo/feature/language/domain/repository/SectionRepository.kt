package com.sermomemo.feature.language.domain.repository

import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.feature.language.domain.model.Section
import com.sermomemo.core.domain.id.SectionId
import kotlinx.coroutines.flow.Flow

interface SectionRepository {

    fun observeByLanguage(languageId: LanguageId): Flow<List<Section>>

    suspend fun findById(id: SectionId): Section?

    suspend fun countByLanguage(languageId: LanguageId): Int

    suspend fun save(section: Section)

    suspend fun saveAll(sections: List<Section>)

    /** Persists a whole reordering in one transaction, so positions never end up inconsistent. */
    suspend fun updatePositions(positions: Map<SectionId, Int>)

    suspend fun delete(id: SectionId)
}
