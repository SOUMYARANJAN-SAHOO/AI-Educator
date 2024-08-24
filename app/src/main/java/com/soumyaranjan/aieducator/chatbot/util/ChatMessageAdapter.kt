package com.soumyaranjan.aieducator.chatbot.util

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.soumyaranjan.aieducator.R
import com.soumyaranjan.aieducator.chatbot.model.Message
import com.soumyaranjan.aieducator.databinding.ChatMessageBinding
import io.noties.markwon.Markwon

class ChatMessageAdapter(private val messageList: List<Message>, private val context: Context): RecyclerView.Adapter<ChatMessageAdapter.ChatMessageViewHolder>(){

    class ChatMessageViewHolder(val binding: ChatMessageBinding, val context: Context) : RecyclerView.ViewHolder(binding.root) {
        fun bind(message: Message){
            val markdown = Markwon.create(context)
            markdown.setMarkdown(binding.chatMessage, message.message)
            if(message.isUser){
                binding.chatMessageLayout.gravity = Gravity.END
                binding.chatMessageCard.backgroundTintList = binding.root.context.getColorStateList(R.color.white)
                binding.chatMessage.setTextColor(binding.root.context.getColor(R.color.black))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatMessageViewHolder {
        val binding = ChatMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChatMessageViewHolder(binding,context)
    }

    override fun getItemCount(): Int {
        return messageList.size
    }

    override fun onBindViewHolder(holder: ChatMessageViewHolder, position: Int) {
        holder.bind(messageList[position])
    }
}