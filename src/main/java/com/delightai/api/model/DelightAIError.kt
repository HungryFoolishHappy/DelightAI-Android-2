package com.example.delightAI.model

data class DelightAIError (
    val message: String?,
    var type: String? = null,
    var code: String? = null,
)