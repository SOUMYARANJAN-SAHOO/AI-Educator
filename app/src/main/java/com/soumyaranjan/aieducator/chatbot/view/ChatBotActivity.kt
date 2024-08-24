package com.soumyaranjan.aieducator.chatbot.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.soumyaranjan.aieducator.R
import com.soumyaranjan.aieducator.chatbot.model.Message
import com.soumyaranjan.aieducator.chatbot.util.ChatMessageAdapter
import com.soumyaranjan.aieducator.chatbot.viewmodel.ChatBotViewModel
import com.soumyaranjan.aieducator.databinding.ActivityChatBotBinding

class ChatBotActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = DataBindingUtil.setContentView<ActivityChatBotBinding>(this,R.layout.activity_chat_bot)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val viewModel = ViewModelProvider(this).get(ChatBotViewModel::class.java)
        viewModel.sendMessage(Message("Hii there,\nYour Buddy is here!!",false))
        viewModel.sendMessage(Message("How can I help you??",false))

        viewModel.messages.observe(this){
            binding.rChatScreen.adapter = ChatMessageAdapter(it,this)
        }

        binding.btnChatSend.setOnClickListener {
            val message = binding.edtChatMessage.text.toString()

            if(message.isNullOrEmpty()) {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            viewModel.sendMessage(Message(message,true))
            binding.edtChatMessage.setText("")
        }
    }
}