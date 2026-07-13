package com.sermomemo.core.domain

/**
 * Outcome of a use case. Failures are values, not exceptions: an exception here would mean a bug,
 * not a rejected input.
 */
sealed interface UseCaseResult<out T> {
    data class Success<T>(val value: T) : UseCaseResult<T>
    data class Failure(val error: DomainError) : UseCaseResult<Nothing>
}

inline fun <T, R> UseCaseResult<T>.map(transform: (T) -> R): UseCaseResult<R> = when (this) {
    is UseCaseResult.Success -> UseCaseResult.Success(transform(value))
    is UseCaseResult.Failure -> this
}
