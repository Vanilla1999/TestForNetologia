package com.example.testfornetologia.utils

import okhttp3.ResponseBody


sealed class ResponseServer<out T> {
    data class Success<out T>(val value: T) : ResponseServer<T>()
    data class FailureServer(
        val errorCode: Int?,
        val errorBody: ResponseBody?,
    ) : ResponseServer<Nothing>()
    object FailureEnternet : ResponseServer<Nothing>()
    object Loading : ResponseServer<Nothing>()
}
sealed class ErrorApp<out T> {
    data class FailureNetwork(
    val errorCode: Int?,
        val errorBody: ResponseBody?,
    ) : ErrorApp<Nothing>()
    object FailureEnternet : ErrorApp<Nothing>()
    data class FailureUnknown<out T>(val value: T) : ErrorApp<T>()
}


