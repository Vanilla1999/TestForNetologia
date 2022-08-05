package com.example.testfornetologia

import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import java.lang.Exception

fun main(){
    runBlocking {

        supervisorScope {
            try {
                val a =  async {
                    throw Exception()
                }
                a.await()
                println("Нет краша")
            }catch (e:Exception){
                println(e.toString())
            }

        }
    }
}