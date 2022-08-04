package com.example.testfornetologia.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T
    ): ResponseServer<T> {
        return withContext(Dispatchers.IO) {
            try {
                ResponseServer.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        ResponseServer.Failure(false, throwable.code(), throwable.response()?.errorBody())
                    }
                    else -> {
                        ResponseServer.Failure(true, null, null)
                    }
                }
            }
        }
    }
}