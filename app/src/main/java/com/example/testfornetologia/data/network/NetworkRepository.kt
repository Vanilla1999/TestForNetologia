package com.example.testfornetologia.data.network

import com.example.testfornetologia.data.network.model.Json
import com.example.testfornetologia.utils.ResponseServer

class NetworkRepositoryImpl:NetworkRepository {
    override fun getDataFromServer():  ResponseServer<Json> {
        TODO("Not yet implemented")
    }
}
interface NetworkRepository{
    fun getDataFromServer(): ResponseServer<Json>
}