package com.example.testfornetologia.data.network

import com.example.testfornetologia.data.network.api.NetworkService
import com.example.testfornetologia.data.network.model.Json
import com.example.testfornetologia.utils.ResponseServer
import com.example.testfornetologia.utils.SafeApiCall
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NetworkRepositoryImpl @Inject constructor(private val networkService:NetworkService):NetworkRepository {
    override suspend fun getDataFromServer(): Flow<ResponseServer<Json>> {
       return flow { safeApiCall(networkService::getJson,this) }
    }
}
interface NetworkRepository:SafeApiCall{
   suspend fun getDataFromServer(): Flow<ResponseServer<Json>>
}