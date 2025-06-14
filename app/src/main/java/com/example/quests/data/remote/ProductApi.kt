package com.example.quests.data.remote

import com.example.quests.data.model.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ProductApi {
    @GET("products/search")
    suspend fun searchProducts(@Query("q") query: String): ProductResponse
}
