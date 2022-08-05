package com.example.testfornetologia.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.withContext
import retrofit2.HttpException

interface SafeApiCall {
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> T,
        collector: FlowCollector<ResponseServer<T>>
    ) {
        try {
            collector.emit(ResponseServer.Loading)
            val result = coroutineScope { withContext(Dispatchers.IO) { apiCall.invoke() }  }
            collector.emit(ResponseServer.Success(result))
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    collector.emit(
                        ResponseServer.FailureServer(
                            throwable.code(),
                            throwable.response()?.errorBody()
                        )
                    )
                }
                else -> {
                    collector.emit(ResponseServer.FailureEnternet)
                }
            }
        }
    }
}