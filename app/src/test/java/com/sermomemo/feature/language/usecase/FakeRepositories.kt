package com.sermomemo.feature.language.usecase

import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.core.domain.id.SectionId
import com.sermomemo.feature.language.domain.model.Language
import com.sermomemo.feature.language.domain.repository.LanguageRepository
import com.sermomemo.feature.language.domain.enums.LanguageStatus
import com.sermomemo.feature.language.domain.model.Section
import com.sermomemo.feature.language.domain.repository.SectionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class FakeLanguageRepository : LanguageRepository {

    val stored = MutableStateFlow<List<Language>>(emptyList())

    override fun observeAll(): Flow<List<Language>> = stored

    override fun observeByStatus(status: LanguageStatus): Flow<List<Language>> =
        stored.map { languages -> languages.filter { it.status == status } }

    override fun observeById(id: LanguageId): Flow<Language?> =
        stored.map { languages -> languages.firstOrNull { it.id == id } }

    override suspend fun findById(id: LanguageId): Language? =
        stored.value.firstOrNull { it.id == id }

    override suspend fun countByStatus(status: LanguageStatus): Int =
        stored.value.count { it.status == status }

    override suspend fun existsWithName(name: String): Boolean =
        stored.value.any { it.name.equals(name, ignoreCase = true) }

    override suspend fun save(language: Language) {
        stored.value = stored.value.filterNot { it.id == language.id } + language
    }

    override suspend fun updateStatus(id: LanguageId, status: LanguageStatus) {
        stored.value = stored.value.map { if (it.id == id) it.copy(status = status) else it }
    }

    override suspend fun delete(id: LanguageId) {
        stored.value = stored.value.filterNot { it.id == id }
    }
}

class FakeSectionRepository : SectionRepository {

    val stored = MutableStateFlow<List<Section>>(emptyList())

    override fun observeByLanguage(languageId: LanguageId): Flow<List<Section>> =
        stored.map { sections -> sections.filter { it.languageId == languageId } }

    override suspend fun findById(id: SectionId): Section? = stored.value.firstOrNull { it.id == id }

    override suspend fun countByLanguage(languageId: LanguageId): Int =
        stored.value.count { it.languageId == languageId }

    override suspend fun save(section: Section) {
        stored.value = stored.value.filterNot { it.id == section.id } + section
    }

    override suspend fun saveAll(sections: List<Section>) {
        stored.value = stored.value + sections
    }

    override suspend fun updatePositions(positions: Map<SectionId, Int>) {
        stored.value = stored.value.map { section ->
            positions[section.id]?.let { section.copy(position = it) } ?: section
        }
    }

    override suspend fun delete(id: SectionId) {
        stored.value = stored.value.filterNot { it.id == id }
    }
}
