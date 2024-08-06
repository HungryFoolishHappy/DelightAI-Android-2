package com.example.delightAI.service

import com.example.delightAI.model.DelightResponse
import com.example.delightAI.model.DelightPollingResponse
import com.example.delightAI.model.DelightRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.GET
import retrofit2.http.Path

internal interface DelightAIService {
    @Headers("Content-Type: application/json")
    @POST("webhook/webwidget/{webhook_id}/")
    suspend fun sendChat(
        @Body delightRequest: DelightRequest, @Path("webhook_id") webhook_id: String
    ): Response<DelightResponse>

    @GET("{polling_url}")
    suspend fun polling(
        @Path("polling_url", encoded = true) polling_url: String
    ): Response<DelightPollingResponse>
}