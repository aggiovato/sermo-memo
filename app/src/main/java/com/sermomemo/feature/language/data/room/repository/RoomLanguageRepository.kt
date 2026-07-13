package com.sermomemo.feature.language.data.room.repository

import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.feature.language.domain.model.Language
import com.sermomemo.feature.language.domain.repository.LanguageRepository
import com.sermomemo.feature.language.domain.enums.LanguageStatus
import com.sermomemo.feature.language.data.room.dao.LanguageDao
import com.sermomemo.feature.language.data.room.mapper.toDomain
import com.sermomemo.feature.language.data.room.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import javax.inject.Inject

/** Adapter: fulfils the domain port with Room. */
class RoomLanguageRepository @Inject constructor(
    private val dao: LanguageDao,
) : LanguageRepository {

    override fun observeAll(): Flow<List<Language>> =
        dao.observeAll().map { entities -> entities.map { it.toDomain() } }

    override fun observeByStatus(status: LanguageStatus): Flow<List<Language>> =
        dao.observeByStatus(status.name).map { entities -> entities.map { it.toDomain() } }

    override fun observeById(id: LanguageId): Flow<Language?> =
        dao.observeById(id.value).map { it?.toDomain() }

    override suspend fun findById(id: LanguageId): Language? = dao.findById(id.value)?.toDomain()

    override suspend fun countByStatus(status: LanguageStatus): Int = dao.countByStatus(status.name)

    override suspend fun existsWithName(name: String): Boolean = dao.existsWithName(name)

    override suspend fun save(language: Language) = dao.upsert(language.toEntity())

    override suspend fun updateStatus(id: LanguageId, status: LanguageStatus) =
        dao.updateStatus(id.value, status.name, Instant.now().toEpochMilli())

    override suspend fun delete(id: LanguageId) = dao.delete(id.value)
}
