package com.delightai.api

import com.example.delightAI.DelightAIClient

abstract class DelightAIClientBuilder() {
}

class ChatBuilder: DelightAIClientBuilder() {
    fun build(): DelightAIClient = DelightAIClient(this)
}

class PollingBuilder: DelightAIClientBuilder() {
    fun build(): DelightAIClient = DelightAIClient(this)
}