package com.example.recipesapp.api

import java.lang.Error

abstract class ResultState<out T> {
    object Loading:ResultState<Nothing>()
    data class Success<T>(val success: T):ResultState<T>()
    data class Error(val error: Throwable):ResultState<Nothing>()
}