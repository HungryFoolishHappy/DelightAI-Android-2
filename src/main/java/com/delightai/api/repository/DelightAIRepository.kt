package com.delightai.api.repository

import com.example.delightAI.model.DelightPollingResponse
import com.example.delightAI.model.DelightRequest
import com.example.delightAI.model.DelightRequestMessage
import com.example.delightAI.model.DelightRequestMessageFrom
import com.example.delightAI.model.DelightResponse
import com.example.delightAI.model.DelightAIError
import com.example.delightAI.service.DelightAIService
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.util.concurrent.TimeUnit

internal class DelightAIRepository {
    private var apiService: DelightAIService? = null

    suspend fun sendChat(text: String, webhook_id: String, user_id: String, username: String, message_id: String): DelightResponse? {

        var webhook_id = webhook_id
        var delightRequest = DelightRequest(message = DelightRequestMessage(
            message_id = message_id,
            date = System.currentTimeMillis(),
            text = text,
            from = DelightRequestMessageFrom(id = user_id, username = username, language_code = "en"))
        )

        getApiService()?.let {
            val response = it.sendChat(delightRequest, webhook_id)

            if (!response.isSuccessful()) {
                print("Send Chat Error")
            }

            return response.body()
        }

        return null
    }

    suspend fun polling(webhook_id: String): DelightPollingResponse? {
        getApiService()?.let {
            val decodedUrl = URLDecoder.decode(webhook_id, StandardCharsets.UTF_8.toString())
            println(decodedUrl)
            val response = it.polling(decodedUrl)

            if (!response.isSuccessful()) {
                print("Polling Error")
            }
            return response.body()
        }
        return null
    }

    private fun getApiService(): DelightAIService? {
        if (apiService == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl("https://qa.delight.global")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()

            apiService = retrofit.create(DelightAIService::class.java)
        }

        return apiService
    }
}