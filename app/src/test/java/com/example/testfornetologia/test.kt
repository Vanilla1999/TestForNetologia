package com.example.testfornetologia

import kotlinx.coroutines.*

fun main(){
    runBlocking {
        try {
        coroutineScope {

            withContext(Dispatchers.Default) {
                throw Exception()
            }
            println("Нет краша")

        }
        }catch (e:Exception){
            println(e.toString())
        }
    }
}