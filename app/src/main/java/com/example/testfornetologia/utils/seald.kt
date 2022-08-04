package com.example.testfornetologia.utils


sealed class ResponseServer<out T> {
    data class Success<out T>(val value: List<T>) : ResponseServer<T>()
    data class Failure(
        val errorBody: Throwable,
    ) : ResponseServer<Nothing>()
    object Empty : ResponseServer<Nothing>()
    object Loading : ResponseServer<Nothing>()
}


