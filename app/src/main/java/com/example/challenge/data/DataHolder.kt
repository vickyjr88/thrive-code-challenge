package com.example.challenge.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DataHolder : ViewModel(){

    init {
        makeApiCalls()
    }

    val message = MutableLiveData<String>()
    val messageFromApi = MutableLiveData<String>()

    fun sendMessage(text: String) {
        viewModelScope.launch {
            delay(5000)
            message.value = text
        }

    }



    fun sendMessageFromApi(text: String) {
        viewModelScope.launch {
            messageFromApi.value = text
        }

    }

    fun makeApiCalls(){
        viewModelScope.launch {
            try {
                val result1 = callApiAndGetResponse(1)
                sendMessageFromApi(result1)
            } catch (e: Exception){
                val result3 = callApiAndGetResponse(3)
                sendMessageFromApi(result3)
                val result1 = callApiAndGetResponse(1)
                sendMessageFromApi(result1)
            }

            try {
                val result2 = callApiAndGetResponse(2)
                sendMessageFromApi(result2)
            } catch (e: Exception){
                val result3 = callApiAndGetResponse(3)
                sendMessageFromApi(result3)
                val result2 = callApiAndGetResponse(2)
                sendMessageFromApi(result2)
            }
        }
    }

    suspend fun callApiAndGetResponse(id: Int): String {
        val rnd = (Math.random() * 10).toLong()
        delay(rnd * 1000) // in milli sec’s
        if ((rnd.toInt() % 5) == 0) throw Exception("call $id failed")
        return "call $id success"
    }
}