package com.example.testfornetologia.data.network

import com.example.testfornetologia.data.network.api.NetworkService
import com.example.testfornetologia.data.network.model.Json
import com.example.testfornetologia.utils.ResponseServer
import com.example.testfornetologia.utils.SafeApiCall
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val networkService:NetworkService):NetworkRepository {
    override suspend fun getDataFromServer():  ResponseServer<Json> {
       return safeApiCall {  networkService.getJson()  }
    }
}
interface NetworkRepository:SafeApiCall{
   suspend fun getDataFromServer(): ResponseServer<Json>
}