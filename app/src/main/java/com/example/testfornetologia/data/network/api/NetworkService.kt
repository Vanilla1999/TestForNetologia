package com.example.testfornetologia.data.network.api

import com.example.testfornetologia.data.network.model.Json
import retrofit2.http.GET

interface NetworkService {
    @GET("netology")
    suspend fun getJson():Json
}