package com.example.testfornetologia.domain


import com.example.testfornetologia.data.network.NetworkRepository
import com.example.testfornetologia.data.network.model.Json
import com.example.testfornetologia.utils.ResponseServer
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDataFromServerUseCaseImpl @Inject constructor(private val remouteRepository: NetworkRepository):
    GetDataFromServerUseCase {
    override suspend fun getDataFromServer():  Flow<ResponseServer<Json>>{
       return remouteRepository.getDataFromServer()
    }

}

interface GetDataFromServerUseCase {
    suspend fun getDataFromServer(): Flow<ResponseServer<Json>>
}
