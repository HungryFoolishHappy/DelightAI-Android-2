package com.example.delightAI

import com.delightai.api.DelightAIClientBuilder
import com.delightai.api.repository.DelightAIRepository
import com.example.delightAI.model.DelightPollingResponse
import com.example.delightAI.model.DelightRequest
import com.example.delightAI.model.DelightRequestMessage
import com.example.delightAI.model.DelightRequestMessageFrom
import com.example.delightAI.model.DelightResponse

class DelightAIClient internal constructor(builder: DelightAIClientBuilder) {
    suspend fun send(text: String, webhook_id: String, user_id: String, username: String, message_id: String): DelightResponse? {
        val delightAI = DelightAIRepository()
        return delightAI.sendChat(text, webhook_id, user_id, username, message_id)
    }

    suspend fun polling(webhook_id: String): DelightPollingResponse? {
        val delightAI = DelightAIRepository()
        return delightAI.polling(webhook_id)
    }
}