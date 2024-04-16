package com.example.recipesapp.domain.usecase

abstract class ResultState<out T> {
    object Loading: ResultState<Nothing>()
    data class Success<T>(val response: T): ResultState<T>()
    data class Error(val error: Throwable): ResultState<Nothing>()
}