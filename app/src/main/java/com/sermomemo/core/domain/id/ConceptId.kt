package com.sermomemo.core.domain.id

import java.util.UUID

/** UUID-backed so the sync layer (phase 5) can merge devices without renumbering anything. */
@JvmInline
value class ConceptId(val value: String) {
    companion object {
        fun new(): ConceptId = ConceptId(UUID.randomUUID().toString())
    }
}
