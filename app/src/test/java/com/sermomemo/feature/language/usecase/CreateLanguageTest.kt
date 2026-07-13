package com.sermomemo.feature.language.usecase

import com.sermomemo.core.domain.DomainError
import com.sermomemo.core.domain.UseCaseResult
import com.sermomemo.feature.language.domain.enums.LanguageStatus
import com.sermomemo.feature.language.domain.enums.SectionType
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class CreateLanguageTest {

    private lateinit var languages: FakeLanguageRepository
    private lateinit var sections: FakeSectionRepository
    private lateinit var createLanguage: CreateLanguage

    private val fixedClock = Clock.fixed(Instant.parse("2026-07-13T10:00:00Z"), ZoneOffset.UTC)

    private val sectionNames = mapOf(
        SectionType.VOCABULARY to "Vocabulario",
        SectionType.GRAMMAR to "Gramática",
        SectionType.EXPRESSIONS to "Expresiones",
        SectionType.ERRORS to "Errores",
        SectionType.NOTES to "Notas",
    )

    @Before
    fun setUp() {
        languages = FakeLanguageRepository()
        sections = FakeSectionRepository()
        createLanguage = CreateLanguage(languages, sections, fixedClock)
    }

    private fun command(name: String = "Français", maxActiveLanguages: Int = 2) = CreateLanguage.Command(
        name = name,
        code = "fr",
        baseLanguageCode = "es",
        level = null,
        colorToken = "amber",
        defaultSectionNames = sectionNames,
        maxActiveLanguages = maxActiveLanguages,
    )

    @Test
    fun `creates an active language with its default sections`() = runTest {
        val result = createLanguage(command())

        assertTrue(result is UseCaseResult.Success)
        val stored = languages.stored.value.single()
        assertEquals("Français", stored.name)
        assertEquals(LanguageStatus.ACTIVE, stored.status)
        assertEquals(fixedClock.instant(), stored.createdAt)

        // A language must never start without structure, otherwise there is nowhere to capture.
        val created = sections.stored.value
        assertEquals(5, created.size)
        assertEquals(listOf(0, 1, 2, 3, 4), created.map { it.position })
        assertTrue(created.all { it.languageId == stored.id })
    }

    @Test
    fun `notes section does not generate practice`() = runTest {
        createLanguage(command())

        val notes = sections.stored.value.single { it.type == SectionType.NOTES }
        assertFalse(notes.practiceEnabled)
        assertTrue(sections.stored.value.filter { it.type != SectionType.NOTES }.all { it.practiceEnabled })
    }

    @Test
    fun `rejects a blank name`() = runTest {
        val result = createLanguage(command(name = "   "))

        assertEquals(UseCaseResult.Failure(DomainError.EmptyName), result)
        assertTrue(languages.stored.value.isEmpty())
    }

    @Test
    fun `rejects a duplicate name regardless of case`() = runTest {
        createLanguage(command(name = "Français"))

        val result = createLanguage(command(name = "français"))

        assertEquals(UseCaseResult.Failure(DomainError.DuplicateLanguageName), result)
        assertEquals(1, languages.stored.value.size)
    }

    @Test
    fun `rejects creation beyond the plan limit and writes nothing`() = runTest {
        createLanguage(command(name = "Français", maxActiveLanguages = 2))
        createLanguage(command(name = "Deutsch", maxActiveLanguages = 2))

        val result = createLanguage(command(name = "Italiano", maxActiveLanguages = 2))

        assertEquals(UseCaseResult.Failure(DomainError.PlanLimitReached(2)), result)
        assertEquals(2, languages.stored.value.size)
        assertEquals(10, sections.stored.value.size)
    }

    @Test
    fun `archived languages do not count against the plan limit`() = runTest {
        createLanguage(command(name = "Français"))
        createLanguage(command(name = "Deutsch"))
        val archived = languages.stored.value.first()
        languages.updateStatus(archived.id, LanguageStatus.ARCHIVED)

        val result = createLanguage(command(name = "Italiano"))

        assertTrue(result is UseCaseResult.Success)
    }
}
