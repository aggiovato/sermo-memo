package com.sermomemo.feature.language.usecase

import com.sermomemo.core.domain.DomainError
import com.sermomemo.core.domain.id.LanguageId
import com.sermomemo.core.domain.id.SectionId
import com.sermomemo.core.domain.UseCaseResult
import com.sermomemo.feature.language.domain.model.Language
import com.sermomemo.feature.language.domain.enums.LanguageStatus
import com.sermomemo.feature.language.domain.enums.SectionType
import com.sermomemo.feature.language.domain.enums.SectionVisibility
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class SectionUseCasesTest {

    private lateinit var languages: FakeLanguageRepository
    private lateinit var sections: FakeSectionRepository
    private lateinit var createSection: CreateSection
    private lateinit var updateSection: UpdateSection
    private lateinit var reorderSections: ReorderSections
    private lateinit var deleteSection: DeleteSection

    private val clock = Clock.fixed(Instant.parse("2026-07-14T09:00:00Z"), ZoneOffset.UTC)

    // A value class cannot be lateinit, so it is seeded here and re-seeded per test.
    private var languageId: LanguageId = LanguageId.new()

    @Before
    fun setUp() = runTest {
        languages = FakeLanguageRepository()
        sections = FakeSectionRepository()
        createSection = CreateSection(languages, sections, clock)
        updateSection = UpdateSection(sections, clock)
        reorderSections = ReorderSections(sections)
        deleteSection = DeleteSection(sections)

        languageId = LanguageId.new()
        languages.save(
            Language(
                id = languageId,
                name = "Français",
                code = "fr",
                baseLanguageCode = "es",
                level = null,
                colorToken = "amber",
                icon = null,
                status = LanguageStatus.ACTIVE,
                createdAt = clock.instant(),
                updatedAt = clock.instant(),
            ),
        )
    }

    private suspend fun create(
        name: String,
        parent: SectionId? = null,
    ): SectionId {
        val result = createSection(
            CreateSection.Command(
                languageId = languageId,
                name = name,
                type = SectionType.CUSTOM,
                parentSectionId = parent,
            ),
        )
        return (result as UseCaseResult.Success).value
    }

    @Test
    fun `new sections are appended after their siblings`() = runTest {
        create("Verbos")
        create("Sustantivos")
        val third = create("Adjetivos")

        val stored = sections.observeByLanguage(languageId).first()
        assertEquals(listOf(0, 1, 2), stored.map { it.position }.sorted())
        assertEquals(2, stored.single { it.id == third }.position)
    }

    @Test
    fun `positions are counted per level, not globally`() = runTest {
        val parent = create("Verbos")
        val firstChild = create("Irregulares", parent = parent)

        // The first child of a parent starts at 0 even though a root section already holds 0.
        assertEquals(0, sections.findById(firstChild)!!.position)
    }

    @Test
    fun `a section cannot hang from its own descendant`() = runTest {
        val parent = create("Verbos")
        val child = create("Irregulares", parent = parent)

        val result = updateSection(
            UpdateSection.Command(
                id = parent,
                name = "Verbos",
                icon = null,
                colorToken = null,
                practiceEnabled = true,
                visibility = SectionVisibility.VISIBLE,
                parentSectionId = child,
            ),
        )

        assertEquals(UseCaseResult.Failure(DomainError.SectionCycle), result)
        assertNull(sections.findById(parent)!!.parentSectionId)
    }

    @Test
    fun `a section cannot hang from itself`() = runTest {
        val section = create("Verbos")

        val result = updateSection(
            UpdateSection.Command(
                id = section,
                name = "Verbos",
                icon = null,
                colorToken = null,
                practiceEnabled = true,
                visibility = SectionVisibility.VISIBLE,
                parentSectionId = section,
            ),
        )

        assertEquals(UseCaseResult.Failure(DomainError.SectionCycle), result)
    }

    @Test
    fun `reorder renumbers the level densely`() = runTest {
        val a = create("Verbos")
        val b = create("Sustantivos")
        val c = create("Adjetivos")

        val result = reorderSections(languageId, parentSectionId = null, orderedIds = listOf(c, a, b))

        assertTrue(result is UseCaseResult.Success)
        assertEquals(0, sections.findById(c)!!.position)
        assertEquals(1, sections.findById(a)!!.position)
        assertEquals(2, sections.findById(b)!!.position)
    }

    @Test
    fun `reorder rejects an order that does not name the whole level`() = runTest {
        val a = create("Verbos")
        create("Sustantivos")

        val result = reorderSections(languageId, parentSectionId = null, orderedIds = listOf(a))

        assertEquals(UseCaseResult.Failure(DomainError.SectionNotFound), result)
    }

    @Test
    fun `the last root section cannot be deleted`() = runTest {
        val only = create("Vocabulario")

        val result = deleteSection(only)

        assertEquals(UseCaseResult.Failure(DomainError.LastSectionCannotBeRemoved), result)
        assertNotNull(sections.findById(only))
    }

    @Test
    fun `a subsection can be deleted even if it is the only one at its level`() = runTest {
        val parent = create("Verbos")
        val child = create("Irregulares", parent = parent)

        val result = deleteSection(child)

        assertTrue(result is UseCaseResult.Success)
        assertNull(sections.findById(child))
    }

    @Test
    fun `hiding a section keeps it`() = runTest {
        create("Vocabulario")
        val notes = create("Notas")

        val result = SetSectionVisibility(sections, clock)(notes, SectionVisibility.HIDDEN)

        assertTrue(result is UseCaseResult.Success)
        assertEquals(SectionVisibility.HIDDEN, sections.findById(notes)!!.visibility)
    }

    @Test
    fun `a section cannot be created in a language that does not exist`() = runTest {
        val result = createSection(
            CreateSection.Command(
                languageId = LanguageId.new(),
                name = "Verbos",
                type = SectionType.CUSTOM,
            ),
        )

        assertEquals(UseCaseResult.Failure(DomainError.LanguageNotFound), result)
    }
}
