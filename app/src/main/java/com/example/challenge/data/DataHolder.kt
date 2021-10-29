package com.example.challenge.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay

class DataHolder : ViewModel(){
    val message = MutableLiveData<String>()

    fun sendMessage(text: String) {
        message.value = text
    }

    suspend fun callApiAndGetResponse(id: Int): String {
        val rnd = (Math.random() * 10).toLong()
        delay(rnd * 1000) // in milli sec’s
        if ((rnd.toInt() % 5) == 0) throw Exception("call $id failed")
        return "call $id success"
    }
}