package com.sermomemo.core.domain

/** Business failures. Anything a user can trigger and the UI must explain in plain words. */
sealed interface DomainError {
    data object EmptyName : DomainError
    data object LanguageNotFound : DomainError
    data object SectionNotFound : DomainError
    data object DuplicateLanguageName : DomainError

    /** A language must keep at least one section while it is active. */
    data object LastSectionCannotBeRemoved : DomainError

    /** Nesting a section under its own descendant would make the tree cyclic. */
    data object SectionCycle : DomainError

    data class PlanLimitReached(val limit: Int) : DomainError
}
