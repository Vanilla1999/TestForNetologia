package com.example.testfornetologia.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T,
        collector: FlowCollector<ResponseServer<T>>
    ) {
        return withContext(Dispatchers.IO + SupervisorJob()) {
            try {
                collector.emit(ResponseServer.Loading)
                val result = async {apiCall.invoke()}
                collector.emit(ResponseServer.Success(result.await()))
            } catch (throwable: Throwable) {
                when (throwable) {
                    is HttpException -> {
                        collector.emit(ResponseServer.FailureServer( throwable.code(), throwable.response()?.errorBody()))
                    }
                    else -> {
                        collector.emit(ResponseServer.FailureEnternet)
                    }
                }
            }
        }
    }
}