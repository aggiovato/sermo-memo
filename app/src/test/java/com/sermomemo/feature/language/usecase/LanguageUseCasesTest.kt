package com.sermomemo.feature.language.usecase

import com.sermomemo.core.domain.DomainError
import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.core.domain.UseCaseResult
import com.sermomemo.feature.language.domain.model.Language
import com.sermomemo.feature.language.domain.enums.LanguageLevel
import com.sermomemo.feature.language.domain.enums.LanguageStatus
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class LanguageUseCasesTest {

    private lateinit var languages: FakeLanguageRepository

    private val createdAt = Instant.parse("2026-07-13T10:00:00Z")
    private val now = Instant.parse("2026-07-14T09:00:00Z")
    private val clock = Clock.fixed(now, ZoneOffset.UTC)

    @Before
    fun setUp() {
        languages = FakeLanguageRepository()
    }

    private suspend fun givenLanguage(
        name: String = "Français",
        status: LanguageStatus = LanguageStatus.ACTIVE,
    ): Language {
        val language = Language(
            id = LanguageId.new(),
            name = name,
            code = "fr",
            baseLanguageCode = "es",
            level = null,
            colorToken = "amber",
            icon = null,
            status = status,
            createdAt = createdAt,
            updatedAt = createdAt,
        )
        languages.save(language)
        return language
    }

    @Test
    fun `update renames and stamps updatedAt without touching createdAt`() = runTest {
        val language = givenLanguage()

        val result = UpdateLanguage(languages, clock)(
            UpdateLanguage.Command(
                id = language.id,
                name = "  Français (Québec)  ",
                code = "fr-CA",
                baseLanguageCode = "es",
                level = LanguageLevel.B1,
                colorToken = "indigo",
                icon = null,
            ),
        )

        assertTrue(result is UseCaseResult.Success)
        val updated = languages.findById(language.id)!!
        assertEquals("Français (Québec)", updated.name)
        assertEquals(LanguageLevel.B1, updated.level)
        assertEquals(createdAt, updated.createdAt)
        assertEquals(now, updated.updatedAt)
    }

    @Test
    fun `update accepts keeping its own name`() = runTest {
        val language = givenLanguage(name = "Français")

        val result = UpdateLanguage(languages, clock)(
            UpdateLanguage.Command(
                id = language.id,
                name = "français",
                code = null,
                baseLanguageCode = null,
                level = null,
                colorToken = "amber",
                icon = null,
            ),
        )

        assertTrue(result is UseCaseResult.Success)
    }

    @Test
    fun `update rejects taking the name of another language`() = runTest {
        givenLanguage(name = "Deutsch")
        val language = givenLanguage(name = "Français")

        val result = UpdateLanguage(languages, clock)(
            UpdateLanguage.Command(
                id = language.id,
                name = "deutsch",
                code = null,
                baseLanguageCode = null,
                level = null,
                colorToken = "amber",
                icon = null,
            ),
        )

        assertEquals(UseCaseResult.Failure(DomainError.DuplicateLanguageName), result)
        assertEquals("Français", languages.findById(language.id)!!.name)
    }

    @Test
    fun `archiving keeps the language and its data`() = runTest {
        val language = givenLanguage()

        val result = ArchiveLanguage(languages)(language.id)

        assertTrue(result is UseCaseResult.Success)
        val archived = languages.findById(language.id)
        assertEquals(LanguageStatus.ARCHIVED, archived!!.status)
    }

    @Test
    fun `unarchiving is blocked when the plan is already full`() = runTest {
        val archived = givenLanguage(name = "Italiano", status = LanguageStatus.ARCHIVED)
        givenLanguage(name = "Français")
        givenLanguage(name = "Deutsch")

        val result = UnarchiveLanguage(languages)(archived.id, maxActiveLanguages = 2)

        assertEquals(UseCaseResult.Failure(DomainError.PlanLimitReached(2)), result)
        assertEquals(LanguageStatus.ARCHIVED, languages.findById(archived.id)!!.status)
    }

    @Test
    fun `unarchiving succeeds when there is room`() = runTest {
        val archived = givenLanguage(name = "Italiano", status = LanguageStatus.ARCHIVED)
        givenLanguage(name = "Français")

        val result = UnarchiveLanguage(languages)(archived.id, maxActiveLanguages = 2)

        assertTrue(result is UseCaseResult.Success)
        assertEquals(LanguageStatus.ACTIVE, languages.findById(archived.id)!!.status)
    }

    @Test
    fun `deleting an unknown language fails instead of doing nothing quietly`() = runTest {
        val result = DeleteLanguage(languages)(LanguageId.new())

        assertEquals(UseCaseResult.Failure(DomainError.LanguageNotFound), result)
    }

    @Test
    fun `get languages filters by status`() = runTest {
        givenLanguage(name = "Français")
        givenLanguage(name = "Italiano", status = LanguageStatus.ARCHIVED)

        val active = GetLanguages(languages)(LanguageStatus.ACTIVE).first()
        val all = GetLanguages(languages)().first()

        assertEquals(listOf("Français"), active.map { it.name })
        assertEquals(2, all.size)
    }
}
