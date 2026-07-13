package com.sermomemo.feature.language.data.room.repository

import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.core.domain.id.SectionId
import com.sermomemo.feature.language.domain.model.Section
import com.sermomemo.feature.language.domain.repository.SectionRepository
import com.sermomemo.feature.language.data.room.dao.SectionDao
import com.sermomemo.feature.language.data.room.mapper.toDomain
import com.sermomemo.feature.language.data.room.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomSectionRepository @Inject constructor(
    private val dao: SectionDao,
) : SectionRepository {

    override fun observeByLanguage(languageId: LanguageId): Flow<List<Section>> =
        dao.observeByLanguage(languageId.value).map { entities -> entities.map { it.toDomain() } }

    override suspend fun findById(id: SectionId): Section? = dao.findById(id.value)?.toDomain()

    override suspend fun countByLanguage(languageId: LanguageId): Int =
        dao.countByLanguage(languageId.value)

    override suspend fun save(section: Section) = dao.upsert(section.toEntity())

    override suspend fun saveAll(sections: List<Section>) =
        dao.upsertAll(sections.map { it.toEntity() })

    override suspend fun updatePositions(positions: Map<SectionId, Int>) =
        dao.updatePositions(positions.mapKeys { (id, _) -> id.value })

    override suspend fun delete(id: SectionId) = dao.delete(id.value)
}
