package com.example.delightAI.model

data class DelightPollingResponse (
    var uuid: String,
    var completed: Boolean,
    var text: String?,
    var new_tokens: String?
)