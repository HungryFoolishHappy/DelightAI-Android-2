package com.example.delightAI.model

data class DelightResponse(
    var text: String,
    var shouldEndConversation: Boolean,
    var poll: String,
    var error: DelightError? = null,
)
