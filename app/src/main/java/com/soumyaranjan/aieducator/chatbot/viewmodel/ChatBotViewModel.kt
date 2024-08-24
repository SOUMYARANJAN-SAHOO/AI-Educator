package com.soumyaranjan.aieducator.chatbot.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.soumyaranjan.aieducator.chatbot.model.Message
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatBotViewModel(): ViewModel() {


    private val _messages = MutableLiveData<MutableList<Message>>()
    val messages: LiveData<MutableList<Message>> = _messages

    val apiKey = "AIzaSyAdur_YJZFl-RYWOQTRohkXpkZo6fPgJQ8"
    val model = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = apiKey
    )


    fun sendMessage(message: Message){
        _messages.value = _messages.value?.apply {
            add(message)
        } ?: mutableListOf(message)

        _messages.value = _messages.value
        if(message.isUser){
            viewModelScope.launch {
                generateResponse(message.message)
            }

        }
    }

    fun deleteMessage(){
        _messages.value = _messages.value?.apply {
            removeLast()
        }
        _messages.value = _messages.value
    }
    suspend fun generateResponse(prompt: String){
        sendMessage(Message("Typing...",false))
        try {
            val chat = model.startChat()
            val response = chat.sendMessage(prompt)
            deleteMessage()
            sendMessage(Message(response.text.toString(),false))
        }catch (e:Exception){
            deleteMessage()
            sendMessage(Message("Something went wrong!!",false))
        }
    }
}