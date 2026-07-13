package com.sermomemo.feature.language.domain.repository

import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.feature.language.domain.model.Language
import com.sermomemo.feature.language.domain.enums.LanguageStatus
import kotlinx.coroutines.flow.Flow

/**
 * Port. The domain states what it needs; infrastructure decides how (Room today, Room + sync
 * later). No Room type ever crosses this boundary.
 */
interface LanguageRepository {

    fun observeAll(): Flow<List<Language>>

    fun observeByStatus(status: LanguageStatus): Flow<List<Language>>

    fun observeById(id: LanguageId): Flow<Language?>

    suspend fun findById(id: LanguageId): Language?

    suspend fun countByStatus(status: LanguageStatus): Int

    suspend fun existsWithName(name: String): Boolean

    suspend fun save(language: Language)

    suspend fun updateStatus(id: LanguageId, status: LanguageStatus)

    suspend fun delete(id: LanguageId)
}
