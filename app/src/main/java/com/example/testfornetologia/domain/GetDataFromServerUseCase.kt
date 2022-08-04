package com.example.testfornetologia.domain


import com.example.testfornetologia.data.network.NetworkRepository
import com.example.testfornetologia.data.network.model.Json
import com.example.testfornetologia.utils.ResponseServer
import kotlinx.coroutines.flow.Flow

class GetDataFromServerUseCaseImpl (private val remouteRepository: NetworkRepository):
    GetDataFromServerUseCase {
    override suspend fun getDataFromServer(): ResponseServer<Json?> {
        TODO("Not yet implemented")
    }

}

interface GetDataFromServerUseCase {
    suspend fun getDataFromServer(): ResponseServer<Json?>
}
