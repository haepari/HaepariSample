package com.example.haeparisample.data.remote.api

import retrofit2.http.*

interface ApiService {

    companion object {
        const val BASE_URL = "https://api.example.com/"
    }

    // Add your API endpoints here
    // Example:
    // @GET("users/{id}")
    // suspend fun getUser(@Path("id") userId: String): Response<UserResponse>
}
