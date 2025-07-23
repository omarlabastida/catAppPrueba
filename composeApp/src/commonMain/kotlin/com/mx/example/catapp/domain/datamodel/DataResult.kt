package com.mx.example.catapp.domain.datamodel

sealed class DataResult<out T, out E : Throwable> {
    data class Success<T>(val data: T) : DataResult<T, Nothing>()
    data class Error<E : Throwable>(val exception: E) : DataResult<Nothing, E>()
}