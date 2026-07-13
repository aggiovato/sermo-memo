package com.sermomemo.feature.language.data.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sermomemo.feature.language.data.room.entity.LanguageEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface LanguageDao {

    @Query("SELECT * FROM languages ORDER BY name COLLATE NOCASE ASC")
    fun observeAll(): Flow<List<LanguageEntity>>

    @Query("SELECT * FROM languages WHERE status = :status ORDER BY name COLLATE NOCASE ASC")
    fun observeByStatus(status: String): Flow<List<LanguageEntity>>

    @Query("SELECT * FROM languages WHERE id = :id")
    fun observeById(id: String): Flow<LanguageEntity?>

    @Query("SELECT * FROM languages WHERE id = :id")
    suspend fun findById(id: String): LanguageEntity?

    @Query("SELECT COUNT(*) FROM languages WHERE status = :status")
    suspend fun countByStatus(status: String): Int

    @Query("SELECT EXISTS(SELECT 1 FROM languages WHERE name = :name COLLATE NOCASE)")
    suspend fun existsWithName(name: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(language: LanguageEntity)

    @Query("UPDATE languages SET status = :status, updated_at = :updatedAt WHERE id = :id")
    suspend fun updateStatus(id: String, status: String, updatedAt: Long)

    @Query("DELETE FROM languages WHERE id = :id")
    suspend fun delete(id: String)
}
