package com.example.delightAI.model

data class DelightError (
    val message: String?,
    var type: String? = null,
    var code: String? = null,
)