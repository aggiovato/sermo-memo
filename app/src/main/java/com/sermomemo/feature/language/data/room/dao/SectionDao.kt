package com.sermomemo.feature.language.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sermomemo.feature.language.data.room.entity.SectionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SectionDao {

    @Query("SELECT * FROM sections WHERE language_id = :languageId ORDER BY position ASC")
    fun observeByLanguage(languageId: String): Flow<List<SectionEntity>>

    @Query("SELECT * FROM sections WHERE id = :id")
    suspend fun findById(id: String): SectionEntity?

    @Query("SELECT COUNT(*) FROM sections WHERE language_id = :languageId")
    suspend fun countByLanguage(languageId: String): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(section: SectionEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(sections: List<SectionEntity>)

    @Query("UPDATE sections SET position = :position WHERE id = :id")
    suspend fun updatePosition(id: String, position: Int)

    /** A reorder is all-or-nothing: half-applied positions would break the unique ordering. */
    @Transaction
    suspend fun updatePositions(positions: Map<String, Int>) {
        positions.forEach { (id, position) -> updatePosition(id, position) }
    }

    @Query("DELETE FROM sections WHERE id = :id")
    suspend fun delete(id: String)
}
