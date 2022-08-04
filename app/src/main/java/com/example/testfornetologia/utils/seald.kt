package com.example.testfornetologia.utils

import okhttp3.ResponseBody


sealed class ResponseServer<out T> {
    data class Success<out T>(val value: T) : ResponseServer<T>()
    data class Failure(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
    ) : ResponseServer<Nothing>()
    object Empty : ResponseServer<Nothing>()
    object Loading : ResponseServer<Nothing>()
}


